## git init/clone <Url> 初始化仓库
* init需要配合remote使用，完成远程库的依赖或创建
```
git init // 本地初始化一个仓库
git remote add origin "https://xxx.git" // 添加远程仓库的依赖
// 遇到
```
## git -- config  配置用户信息:system（系统）、global（所有项目）、local（当前项目），编辑器
* 查
```
git config --local --list
git config --local user.name
```
* 增
```
git config --local --add user.name "xxx"'
```
* 删
```
git config --local --unset user.name
```
* 改
```
git config --local user.name "xxx"
```
* 编辑器设置 ？

## git -- branch 创建、查看、删除，拉取
* 创建本地分支
```
git branch test master 基于master分支创建test分支
git checkout -b test master 基于master分支创建test分支,并切换到test分支
```
* 查看分支
```
git branch 列出当前分支清单
```
* 删除本地分支
```
git branch -d test 删除本地test分支
git branch -D test test分支还没有合入当前分支，所以要用-D参数才能删掉。
```
* 删除远程分支
```
git push origin --delete test 删除远程test分支
```
* 拉取分支
```
git fetch origin test ==> git merge origin/test 同步远程test分支的数据到本地test并进行merge
git pull test
```

## git log 查看历史提交记录
## git reset 回退版本
## git tag 版本标签
## git remote 远程版本库
## git revert 撤销提交
## git rebase 变基-合并Commit
## git stash 临时缓存区