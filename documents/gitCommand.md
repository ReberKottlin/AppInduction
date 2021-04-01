## 一、git init/clone <Url> 初始化仓库
* **init需要配合remote使用，完成远程库的依赖或创建**
```
git init // 本地初始化一个仓库
git remote add <origin> https://xxx.git // 添加远程仓库的依赖
// 遇到提示：refusing to merge unrelated histories，因为远程仓库已经存在代码记录了，并且那部分代码没有和本地仓库进行关联，可以通过在git pull 后面加--allow-unrelated-histories进行关联
git pull origin <master> --allow-unrelated-histories
```
* **git clone "https://xxx.git" 直接添加Url**

## 二、git remote 远程版本库
```
git remote add <origin> https://xxx.git     // 添加仓库关联
git remote -v                               // 查看仓库关联列表
git remote rename <origin> <newOrigin>      // 修改仓库关联名称
git remote remove <origin>                  // 删除远程仓库的关联
git remote set-url <origin> https://xxx.git // 更新远程仓库的url
```

## 三、git config  配置用户信息:system（系统）、global（所有项目）、local（当前项目）和编辑器配置
* **config的属性配置**
```
git config --local --list                // 显示local配置的属性列表
git config --local user.name             // 查询user.name的属性值
git config --local --add user.name "xxx" // 添加一条属性 user.name
git config --local --unset user.name     // 删除 user.name
git config --local user.name "xxx"       // 更改 user.name的值
```

* **编辑器配置 ？**
``I`` --> 进入enter模式
``esc`` --> 退出enter模式
``shif`` + ``Q``/``:`` --> 底部是否保存模式 --> ``q``(退出不保存)/``qw``(保存退出)

## 四、git branch 创建、查看、删除，拉取
* **创建本地分支**
```
git branch <test> <master>      // 基于master分支创建test分支
git checkout -b <test> <master> // 基于master分支创建test分支,并切换到test分支
```

* **查看分支**
```
git branch 列出当前分支清单
```

* **删除本地分支**
```
git branch -d <test> // 删除本地test分支
git branch -D <test> // test分支还没有合入当前分支，所以要用-D参数才能删掉。
```

* **删除远程分支**
```
git push origin --delete <test> // 删除远程test分支
```

* **拉取分支**
```
git fetch origin <test> ==> git merge <origin/test> // 同步远程test分支的数据到本地test并进行merge
git pull test
```

## 五、git log 查看历史提交记录
显示分支最近4条的提交记录，oneline：每一个提交压缩到了一行中。它默认只显示提交ID和提交信息的第一行
```
git log --oneline -4          // 当前分支
git log <master> --oneline -4 // master分支
```

## 六、git reflog 恢复本地操作/误操作
git reflog 可以查看所有分支的所有操作记录（包括（包括commit和reset的操作），包括已经被删除的commit记录
```
git reflog -n // 查看最近的n条操作记录
// 当操作失误后，根据操作记录恢复，可以如下操作：
git reset --hard <reflogId> // 回退到指定的操作记录位置
git cherry-pick <reflogId>  // pick一个需要的操作记录到当前分支
```

## 七、git tag 版本标签
* **查看标签**
```
git tag --list       // 查看本地标签,可以省略"--list"或者"-l""
git ls-remote --tags // 查看远程标签
git show <v1.0.0>    // 查看标签内容
```

* **创建标签**
```
git tag <v1.0.0> // 基于当前分支打标签
git tag <v1.0.0> <commitId> // 基于某个提交commitId打标签
git tag <v1.0.0 -m> "content,xx,xx,xx" <commitId> // 基于某个提交commitId打标签并添加注释
```

* **删除标签**
```
git tag --delete <v1.0.0>           // 删除本地标签
git push <origin> --delete <v1.0.0> // 删除远程标签，类似删除一个远程分支
```

* **上传标签** 
```
git push <origin> <v1.0.0> // 将本地某个特定标签推送到远程
git push <origin> --tags   // 将本地标签一次性推送到远程
```

## 八、git stash 临时缓存区：把当前未提交的修改暂存起来
```
git stash list               // 查看stash列表
git stash save "stash的内容"  // 创建一个stash
git stash pop <stash@{n}/n>  // 将stash的内容弹出，stash@{n}/n 可以不加，默认弹出最近创建的一条
git stash drop <stash@{n}/n> // 移除一个stash内容,stash@{n}/n 可以不加,默认移除最近创建的一条
git stash clear              // 清除所有的stash条目
git show <stash@{n}>         // 展示stash内容
```

## 九、git commit 代码提交
```
git commit -m “message”      // 添加提交注释
git commit --amend           // 显示编辑器来修改最近一次提交的注释内，
git commit --amend --no-edit // 将当前git add后的的内容插入到最近一个commit中，类似覆盖提交不更改注释内容
```

## 十、git rebase 变基-合并Commit
* **rebase的commit合并**
```
git rebase <branchName> // 合并branchName分支到当前分支
git rebase <commitId>   // 合并提交commintId之前的内容到当前分支
git rebase -i HEAD~n    // 最近提交的n个commit合并
git rebase -i <startCommitId> <endCommitId> // 多个提交CommitId范围合并
```

* **冲突后的持续命令**
--continue 当处理完冲突后，可以使用该命令让rebase继续
--abort 终止rebase操作，将HEAD设置为rebase之前的分支
--quit 终止rebase操作，但不会将HEAD重置为原来的分支。index和working tree将不会发生任何改变
--merge 进行rebase操作的时候，同时使用merge进行代码合并

## 十一、git revert 撤销提交
如果revert后，修改完冲突，可执行git revert --continue/abort
```
git revert <commitId>         // revert指定的commitId
git revert X..Y               // X...Y，代表一个左开右闭区间(X,Y],不包括X,包括Y. 其中Y为起点commit，X为终点commit的下一个commit
git revert -m <1/2> <cmmitId> // revert指定的merge commitId
```

## 十二、git reset 回退版本
```
git reset // 把添加到暂缓区的文件移除入工作区
git reset --mixed <commitId> // 回退到指定的commit位置，并将commit之后的改动放入工作区
git reset --hard <commitId>  // 回退到指定的commit位置，并将commit之后的改动移除
git reset --soft <commitId>  // 回退到指定的commit位置，并将commit之后的改动放入暂缓区
```

## 十三、git pull/fetch 代码更新
pull到效果与fetch+merge的效果是相同的，在其他分支需要merge主分支文件，建议使用fetch/merge
```
git pull --rebase 
git fetch <origin> <branchName> // 将远程分支branchName的文件更新到本地仓库缓存区
git merge <origin/master>       // 将本地仓库缓存区的最新文件merge同步到本地仓库
```

## 十四、git push 代码上传
```
git push <origin> <branchName>             // 将代码提交到远程分支
git push <origin> <branchName> --force(-f) // 覆盖提交，多人开发建议不要用，引发冲突问题
git push origin --delete <branchName>      // 通过push删除远程分支
```

## 十五、git cherry-pick 代码上传
```
git cherry-pick <commitId>               // 将其他分支的某个commit复制到当前分支
git cherry-pick -n <commitId> <commitId> // 将其他分支的多个commit复制到当前分支
```
