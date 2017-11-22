# 给运维的一个redis demo

```
设置一个redis key的值
~ curl -i -XPOST 'http://localhost:9000/v1/demo/redis?key=lcx&value=123'
HTTP/1.1 200 OK
{"status":"OK","body":null}


获取一个redis key的值
~ curl -i -XGET 'http://localhost:9000/v1/demo/redis?key=lcx'
HTTP/1.1 200 OK
{"status":"OK","body":"123"}


删除一个redis key
~ curl -i -XDELETE 'http://localhost:9000/v1/demo/redis?key=lcx'
HTTP/1.1 200 OK
{"status":"OK","body":null}
```