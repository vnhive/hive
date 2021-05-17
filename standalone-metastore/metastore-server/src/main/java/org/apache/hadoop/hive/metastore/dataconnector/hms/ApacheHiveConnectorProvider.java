package org.apache.hadoop.hive.metastore.dataconnector.hms;

import static org.apache.hadoop.hive.metastore.conf.MetastoreConf.ConfVars;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.DataConnector;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.conf.MetastoreConf;
import org.apache.hadoop.hive.metastore.dataconnector.AbstractDataConnectorProvider;
import org.apache.hadoop.hive.metastore.dataconnector.IDataConnectorProvider;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.util.List;

public class ApacheHiveConnectorProvider implements IDataConnectorProvider {
  private static Logger LOG = LoggerFactory.getLogger(ApacheHiveConnectorProvider.class);
  private IMetaStoreClient hmsClient = null;
  private String scoped_db = null;
  private boolean isOpen = false;
  Configuration conf = null;

  public ApacheHiveConnectorProvider(String dbName, DataConnector dataConn) {
    conf = MetastoreConf.newMetastoreConf();
    conf.set(ConfVars.THRIFT_URIS.getVarname(), dataConn.getUrl());
    conf.set(ConfVars.FILTER_HOOK.getVarname(),
        "org.apache.hadoop.hive.ql.security.authorization.plugin.metastore.HiveMetaStoreAuthorizer");
    conf.setBoolean(ConfVars.METASTORE_CLIENT_FILTER_ENABLED.getVarname(), false);
    conf.set(ConfVars.URI_RESOLVER.getVarname(), "");
    conf.setBoolean("hive.server2.enable.doAs", true);
    // conf.set(ConfVars.METASTORE_METADATA_TRANSFORMER_CLASS.getVarname(), "");
    // conf.unset(ConfVars.METASTORE_METADATA_TRANSFORMER_CLASS.getVarname());
    // conf.setInt("hive.metastore.connect.retries", 10);
    HiveMetaStoreClient.setProcessorIdentifier("RemoteHiveConnector");
    HiveMetaStoreClient.setProcessorCapabilities(new String[] {
        "MANAGERAWMETADATA", "ACCEPTS_UNMODIFIED_METADATA",
        "HIVEFULLACIDREAD", "HIVEFULLACIDWRITE",
        "EXTWRITE", "EXTREAD"
    } );
  }

  /**
   * Opens a transport/connection to the datasource. Throws exception if the connection cannot be established.
   * @throws MetaException Throws MetaException if the connector does not have all info for a connection to be setup.
   * @throws ConnectException if the connection could not be established for some reason.
   */
  @Override public void open() throws ConnectException, MetaException {
    hmsClient = new HiveMetaStoreClient(conf, null, false);
    isOpen = true;
  }

  /**
   * Closes a transport/connection to the datasource.
   * @throws ConnectException if the connection could not be closed.
   */
  @Override public void close() throws ConnectException {
    if (hmsClient != null) {
      hmsClient.close();
      isOpen = false;
      hmsClient = null;
    }

  }

  /**
   * Set the scope of this object.
   * @param databaseName
   */
  @Override public void setScope(String databaseName) {
    scoped_db = databaseName;
  }

  /**
   * Returns Hive Table objects from the remote database for tables that match a name pattern.
   * @return List A collection of objects that match the name pattern, null otherwise.
   * @throws MetaException To indicate any failures with executing this API
   * @param regex
   */
  @Override
  public List<Table> getTables(String regex) throws MetaException {
    try {
      if (!isOpen)
        open();
      return hmsClient.getTableObjectsByName(scoped_db, getTableNames());
    } catch (ConnectException e) {
      throw  new MetaException("Unexpected connection exception " + e.toString());
    } catch (TException e) {
      throw  new MetaException("Unexpected thrift exception " + e.toString());
    }
  }

  /**
   * Returns a list of all table names from the remote database.
   * @return List A collection of all the table names, null if there are no tables.
   * @throws MetaException To indicate any failures with executing this API
   */
  @Override
  public List<String> getTableNames() throws MetaException {
    try {
      if (!isOpen)
        open();
      return hmsClient.getTables(scoped_db, null);
    } catch (ConnectException e) {
      throw  new MetaException("Unexpected connection exception " + e.toString());
    } catch (TException e ) {
      throw  new MetaException("Unexpected thrift exception " + e.toString());
    }
  }

  /**
   * Fetch a single table with the given name, returns a Hive Table object from the remote database
   * @return Table A Table object for the matching table, null otherwise.
   * @throws MetaException To indicate any failures with executing this API
   * @param tableName
   */
  @Override
  public Table getTable(String tableName) throws MetaException {
    try {
      if (!isOpen)
        open();
      return hmsClient.getTable(scoped_db, tableName);
    } catch (ConnectException e) {
      throw  new MetaException("Unexpected connection exception " + e.toString());
    } catch (TException e) {
      throw  new MetaException("Unexpected thrift exception " + e.toString());
    }
  }

  /**
   * Creates a table with the given name in the remote data source. Conversion between hive data types
   * and native data types is handled by the provider.
   * @param table A Hive table object to translate and create in remote data source.
   * @return boolean True if the operation succeeded or false otherwise
   * @throws MetaException To indicate any failures in executing this operation.
   */
  @Override public boolean createTable(Table table) throws MetaException {
    throw new MetaException("Unsupported operation:create_table");
  }

  /**
   * Drop an existing table with the given name in the remote data source.
   * @param tableName Table name to drop from the remote data source.
   * @return boolean True if the operation succeeded or false otherwise
   * @throws MetaException To indicate any failures in executing this operation.
   */
  @Override public boolean dropTable(String tableName) throws MetaException {
    throw new MetaException("Unsupported operation:drop_table");
  }

  /**
   * Alter an existing table in the remote data source.
   * @param tableName Table name to alter in the remote datasource.
   * @param newTable New Table object to modify the existing table with.
   * @return boolean True if the operation succeeded or false otherwise.
   * @throws MetaException To indicate any failures in executing this operation.
   */
  @Override public boolean alterTable(String tableName, Table newTable) throws MetaException {
    throw new MetaException("Unsupported operation:alter_table");
  }
}
