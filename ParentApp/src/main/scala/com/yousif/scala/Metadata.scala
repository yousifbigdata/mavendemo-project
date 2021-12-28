package com.yousif.scala

object Metadata {
  final case class JobConfDtl(
                               sourceName:String,
                               inputFileType: String,
                               dataFileDelimiter: String,
                               inputSourcePath: String,
                               rejectedRecordsPath: String,
                               rejectOutputType: String,
                               rejectTables: List[String],
                               saveMode: String,
                               processingSuffix: String,
                               outputFormat: String,
                               header: String,
                               partitionColumns: String,
                               inputSchema: List[SchemaDtl]
                             )
  final case class SchemaDtl(columnName:String,columnType: String,isNullable:Boolean)

}
