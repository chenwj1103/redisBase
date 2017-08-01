
1. 在dev的远程分支下创建一个分支
  git checkout -b issue1 origin/dev
  开发...
  git add .
  git commit -m "issue #2"

  
2. 此时合并dev分支的最新的代码
  git pull --rebase

3. 有冲突解决冲突（此时rebase命令会中断，解决冲突后执行此命令）
  git rebase --continue

  如果你想跳过该patch
  git rebase --skip

  如果你想放弃rebase该分支
  git rebase --abort


4. 关联被追踪的远端分支
  git branch --set-upstream-to=origin/<branch>  your-local-branch


5. 查看追踪的远端分支
  git branch -vv