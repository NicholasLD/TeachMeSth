# TeachMeSth - 生成 "教我xxx" 表情包
基于 Mirai Console 的 "教我xxx" 表情包生成插件

![教我K8S](https://img.nicholasld.cn/i/2023/05/30/6474df7dccc59.png)

## 使用方法
1. 下载本项目的 `.jar` 文件
2. 将 `.jar` 文件放入 Mirai Console 的 `plugins` 文件夹
3. 重启 Mirai Console
4. 在群内或私聊发送 `教我xxx` 即可

## 修改字体
1. 下载字体文件
2. 将字体文件放入源代码的 `src/main/resources` 文件夹，并改名为 `font.otf`
3. 使用 Gradle 重新打包
4. 将生成的 `.jar` 文件放入 Mirai Console 的 `plugins` 文件夹
5. 重启 Mirai Console
