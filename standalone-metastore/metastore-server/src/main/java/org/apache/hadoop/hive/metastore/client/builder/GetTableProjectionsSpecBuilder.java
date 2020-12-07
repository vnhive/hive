package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.GetProjectionsSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for the GetProjectionsSpec. This is a projection specification for tables returned from the HMS.
 */
public class GetTableProjectionsSpecBuilder {

    private List<String> columnList = null;
    private String includeColumnPattern = null;
    private String excludeColumnPattern = null;

    public GetTableProjectionsSpecBuilder(List<String> columnList, String includeColumnPattern,
                                          String excludeColumnPattern) {
        this.columnList = columnList;
        this.includeColumnPattern = includeColumnPattern;
        this.excludeColumnPattern = excludeColumnPattern;
    }

    public GetTableProjectionsSpecBuilder() {
    }

    public GetTableProjectionsSpecBuilder setColumnList(List<String> columnList) {
        this.columnList = columnList;
        return this;
    }

    public GetTableProjectionsSpecBuilder setIncludeColumnPattern(String includeColumnPattern) {
        this.includeColumnPattern = includeColumnPattern;
        return this;
    }

    public GetTableProjectionsSpecBuilder setExcludeColumnPattern(String excludeColumnPattern) {
        this.excludeColumnPattern = excludeColumnPattern;
        return this;
    }

    private void initColumnListAndAddCol(String colName) {
        if (this.columnList == null) {
            this.columnList = new ArrayList<String>();
        }
        this.columnList.add(colName);
    }

    public GetTableProjectionsSpecBuilder includeTableName() {
        initColumnListAndAddCol("tableName");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeDatabase() {
        initColumnListAndAddCol("dbName");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdCdCols() {
        initColumnListAndAddCol("sd.cd.cols");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdLocation() {
        initColumnListAndAddCol("sd.location");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdInputFormat() {
        initColumnListAndAddCol("sd.inputFormat");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdOutputFormat() {
        initColumnListAndAddCol("sd.outputFormat");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdIsCompressed() {
        initColumnListAndAddCol("sd.isCompressed");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdNumBuckets() {
        initColumnListAndAddCol("sd.numBuckets");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoName() {
        initColumnListAndAddCol("sd.serdeInfo.name");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoSerializationLib() {
        initColumnListAndAddCol("sd.serdeInfo.serializationLib");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoParameters() {
        initColumnListAndAddCol("sd.serdeInfo.parameters");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoDescription() {
        initColumnListAndAddCol("sd.serdeInfo.description");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoSerializerClass() {
        initColumnListAndAddCol("sd.serdeInfo.serializerClass");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoDeserializerClass() {
        initColumnListAndAddCol("sd.serdeInfo.deserializerClass");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSerDeInfoSerdeType() {
        initColumnListAndAddCol("sd.serdeInfo.serdeType");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdBucketCols() {
        initColumnListAndAddCol("sd.bucketCols");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSortCols() {
        initColumnListAndAddCol("sd.sortCols");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdparameters() {
        initColumnListAndAddCol("sd.parameters");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSkewedColNames() {
        initColumnListAndAddCol("sd.skewedColNames");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSkewedColValues() {
        initColumnListAndAddCol("sd.skewedColValues");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdSkewedColValueLocationMaps() {
        initColumnListAndAddCol("sd.skewedColValueLocationMaps");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeSdIsStoredAsSubDirectories() {
        initColumnListAndAddCol("sd.isStoredAsSubDirectories");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeOwner() {
        initColumnListAndAddCol("owner");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeOwnerType() {
        initColumnListAndAddCol("ownerType");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeCreateTime() {
        initColumnListAndAddCol("createTime");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeLastAccessTime() {
        initColumnListAndAddCol("lastAccessTime");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeRetention() {
        initColumnListAndAddCol("retention");
        return this;
    }

    public GetTableProjectionsSpecBuilder includePartitionKeys() {
        initColumnListAndAddCol("partitionKeys");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeParameters() {
        initColumnListAndAddCol("parameters");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeViewOriginalText() {
        initColumnListAndAddCol("viewOriginalText");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeViewExpandedText() {
        initColumnListAndAddCol("viewExpandedText");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeRewriteEnabled() {
        initColumnListAndAddCol("rewriteEnabled");
        return this;
    }

    public GetTableProjectionsSpecBuilder includeTableType() {
        initColumnListAndAddCol("tableType");
        return this;
    }

    public GetProjectionsSpec build() {
        return new GetProjectionsSpec(columnList, includeColumnPattern, excludeColumnPattern);
    }
}