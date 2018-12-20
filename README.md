# Springboot-Demo

## Module
> Each modules can run independently.<br>
  Different modules can be associated by establishing an intermediate table, which is also convenient for querying.

- **core**: this demo's basic and all module needs to rely on it 
- **filemanage**: CURD and upload/download/FTP about file manage
- **content**: this module like CMS's "c", content management 
- **comment**: this module just record Visitor's comment. No associated the User.Temporarily, the user in the authshiro module 
- **authshiro**: about auth in all project, it use shiro

## Structure
> The structure of all modules is as follows

```
com.anan.springboot.XXX
|-enums
|-exception
|-form         // like the dto
|-orm          // pojo
|-repository   // Operational database./jpa/mybaits
|-service
|-util
|-dto          // Convert the  from's attributes into pojo for Operating the database   
```
 







