# 文件管理模块 /filemanager

## 列表【GET /file 】
> 无分页

http://localhost:8087/filedemo/fileManager/file

响应：

```json
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "id": 3,
      "name": "文件夹",
      "fileType": {
        "id": 102,
        "dictType": "file_file_type",
        "optionValue": "DIR",
        "label": "文件夹",
        "showOrder": 1,
        "editable": 1
      },
      "remark": "文件夹",
      "createTime": 1534781961000,
      "updateTime": 1534816949000
    },
    {
      "id": 9,
      "name": "file_DIR",
      "parent": {
        "id": 3,
        "name": "文件夹",
        "fileType": {
          "id": 102,
          "dictType": "file_file_type",
          "optionValue": "DIR",
          "label": "文件夹",
          "showOrder": 1,
          "editable": 1
        },
        "remark": "文件夹",
        "createTime": 1534781961000,
        "updateTime": 1534816949000
      },
      "fileType": {
        "id": 102,
        "dictType": "file_file_type",
        "optionValue": "DIR",
        "label": "文件夹",
        "showOrder": 1,
        "editable": 1
      },
      "remark": "file_DIR",
      "createTime": 1534816873000,
      "updateTime": 1534816873000
    }
  ]
}
```

| 参数名称        | 参数类型      | 说明        |
| ----------- | --------- | --------- |
|id|Integer|file_file表主键|
|name|String|文件名称|
|fileType|DictOption|字典表类对应的信息|
|parent|File|该数据的父节点信息|
|remark|String|备注|
|createTime|Timestamp|创建时间|
|updateTime|Timestamp|更新时间|

## 单个详情【GET /file/{id}】

http://localhost:8087/filedemo/fileManager/file/{id}

响应如上，参考列表响应


## 新建文件夹【POST  /file】
> 按照逻辑，文件是上传 /io

http://localhost:8087/filedemo/fileManager/file

请求：

```
{
	"name":"fileDirName",
	"parentId":3,
	"fileTypeId":102,
	"remark":"testtest"
}
```

| 参数名称        | 参数类型      | 说明        |
| ----------- | --------- | --------- |
|name|String|文件名称（必填）|
|fileTypeId|int|字典表类id（必填）|
|parentId|int|该数据的父节点id，不传该字段则为根节点下|
|remark|String|备注|

响应：
```json
{"code":0,"msg":"成功"}
```


##  更新文件信息【PUT  /file/{id}】

http://localhost:8087/filedemo/fileManager/file/{id}

请求：

```json
{
	"name":"file_DIR",
	"parentId":12,
	"fileTypeId":102,
	"remark":"testtest"
}
```

| 参数名称        | 参数类型      | 说明        |
| ----------- | --------- | --------- |
|name|String|文件名称（必填）|
|parentId|int|该数据的父节点id,不传该字段则为根节点下|
|fileTypeId|int|字典表类id（必填）|
|remark|String|备注|

响应：
```json
{"code":0,"msg":"成功"}
```

##  删除文件/文件夹【DELETE /file/{id}/{force}】

http://localhost:8087/filedemo/fileManager/file/{id}/{force}

请求：
```
eg:
http://localhost:8087/filedemo/fileManager/file/1,2,3,4/1
http://localhost:8087/filedemo/fileManager/file/10/true
```

| 参数名称        | 参数类型      | 说明        |
| ----------- | --------- | --------- |
|id|String|主键 1,2,3,4,5 （必填）|
|force|boolean| true / false /1 /0 （必填）|

响应：
```json
{"code":0,"msg":"成功"}
```

##  relation【GET  /manage/holiday/relation】

响应：

```json
{
  "data": {
    "natureType": [
      {
        "value": "RELAX",
        "label": "休息"
      },
      {
        "value": "CLASS",
        "label": "补班"
      }
    ]
  },
  "code": 0,
  "message": ""
}
```