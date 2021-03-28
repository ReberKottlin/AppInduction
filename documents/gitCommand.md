## git init/clone <Url> 初始化仓库
* **init需要配合remote使用，完成远程库的依赖或创建**
```
git init // 本地初始化一个仓库
git remote add origin https://xxx.git // 添加远程仓库的依赖
// 遇到提示：refusing to merge unrelated histories，因为远程仓库已经存在代码记录了，并且那部分代码没有和本地仓库进行关联，可以通过在git pull 后面加--allow-unrelated-histories进行关联
git pull origin master --allow-unrelated-histories
```
* **git clone "https://xxx.git" 直接添加Url**

## git remote 远程版本库
```
git remote add origin https://xxx.git     // 添加仓库关联
git remote -v                             // 查看仓库关联列表
git remote rename origin newOrigin        // 修改仓库关联名称
git remote remove origin                  // 删除远程仓库的关联
git remote set-url origin https://xxx.git // 更新远程仓库的url
```

## git -- config  配置用户信息:system（系统）、global（所有项目）、local（当前项目），编辑器
* **查**
```
git config --local --list
git config --local user.name
```
* **增**
```
git config --local --add user.name "xxx"
```
* **删**
```
git config --local --unset user.name
```
* **改**
```
git config --local user.name "xxx"
```
* **编辑器设置 ？**

## git -- branch 创建、查看、删除，拉取
* **创建本地分支**
```
git branch test master      // 基于master分支创建test分支
git checkout -b test master // 基于master分支创建test分支,并切换到test分支
```
* **查看分支**
```
git branch 列出当前分支清单
```
* **删除本地分支**
```
git branch -d test // 删除本地test分支
git branch -D test // test分支还没有合入当前分支，所以要用-D参数才能删掉。
```
* **删除远程分支**
```
git push origin --delete test // 删除远程test分支
```
* **拉取分支**
```
git fetch origin test ==> git merge origin/test // 同步远程test分支的数据到本地test并进行merge
git pull test
```

## git log 查看历史提交记录
显示分支最近4条的提交记录，oneline：每一个提交压缩到了一行中。它默认只显示提交ID和提交信息的第一行
```
git log --oneline -4        // 当前分支
git log master --oneline -4 // master分支
```

## git tag 版本标签
* **查看标签**
```
git tag --list       // 查看本地标签,可以省略"--list"或者"-l""
git ls-remote --tags // 查看远程标签
git show v1.0.0      // 查看标签内容
```

* **创建标签**
```
git tag v1.0.0 // 基于当前分支打标签
git tag v1.0.0 xxx(commitId) // 基于某个提交commitId打标签
git tag v1.0.0 -m "content,xx,xx,xx" xxx(commitId) // 基于某个提交commitId打标签并添加注释
```

* **删除标签**
```
git tag --delete v1.0.0         // 删除本地标签
git push origin --delete v1.0.0 // 删除远程标签，类似删除一个远程分支
```

* **上传标签** 
```
git push origin v1.0.0 // 将本地某个特定标签推送到远程
git push origin --tags // 将本地标签一次性推送到远程
```
## git stash 临时缓存区：把当前未提交的修改暂存起来
```
git stash list              // 查看stash列表
git stash save "stash的内容" // 创建一个stash
git stash pop stash@{n}     // 将stash的内容弹出，stash@{n} 可以不加，默认弹出最上面的那条
git stash drop stash@{n}    // 移除一个stash内容
git stash clear             // 清除所有的stash条目
git show stash@{n}          // 展示stash内容
```

## git reset 回退版本
## git revert 撤销提交
## git rebase 变基-合并Commit
