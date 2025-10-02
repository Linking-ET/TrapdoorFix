# TrapdoorFix | 活版门跳略修复

一款轻量级 **Spigot/Paper** 插件：  
当 **关闭的活版门** 被 **红石信号激活** 时，自动**拆除其上方的红石线**，并**掉落 1 个红石粉**作为补偿。  
可用于防止更新抑制崩服，或作为优化跳略器卡顿。

---

## 主要功能

- 支持**基于标签的所有活版门**（橡木、云杉、白桦、丛林、金合欢、深色橡木、绯红、诡异、铜……）
- **黑白名单模式**自由切换，`config.yml` 任意配置
- 拥有热重载命令
- 兼容 **Java 17+** 与 **Minecraft 1.13+**

---

## 下载

1.  [Releases](https://github.com/Linking-ET/TrapdoorFix/releases)
2. 自行编译：

```bash
./gradlew build
# 输出 -> release/TrapdoorFix-1.0-SNAPSHOT.jar
```

---

## 安装步骤

1. 把 `TrapdoorFix-1.0-SNAPSHOT.jar` 扔进服务器 `plugins` 文件夹
2. 启动一次，插件会自动生成默认配置
3. 按需修改 `plugins/TrapdoorFix/config.yml`，然后游戏内输入
   ```
   /trapdoorfix reload
   ```

---

## 命令 & 权限

| 命令                    | 说明               | 权限节点              | 默认拥有 |
|-------------------------|--------------------|-----------------------|----------|
| `/trapdoorfix reload`   | 重载配置文件       | `trapdoorfix.reload`  | OP       |

---

## 配置文件

`plugins/TrapdoorFix/config.yml`
```yaml
# 总开关
enabled: true

# 名单模式
# BLACKLIST = 名单内的材质才触发拆除
# WHITELIST = 名单内的材质不触发拆除
mode: BLACKLIST

# 材质列表（大写），留空代表全部启用
# 示例：
# list: [CRIMSON_TRAPDOOR, WARPED_TRAPDOOR]
list: []
```

改完执行 `/trapdoorfix reload` 即可热应用。

---

## 触发逻辑

1. 陷阱门**上方一格**必须是 **红石线**
2. 陷阱门**处于关闭状态**
3. 红石信号从 **0 → 正数**（被激活）
4. 瞬间删除红石线，并在陷阱门位置**掉落 1 个红石粉**

---

## 自行编译

环境要求：**JDK 17 及以上**

```bash
git clone https://github.com/Linking-ET/TrapdoorFix.git
cd TrapdoorFix
./gradlew clean build
```

编译产物在 `build/libs` 目录。

---


## 反馈 & 源码

- 提交 Issue / PR：[GitHub 仓库](https://github.com/Linking-ET/TrapdoorFix)
- 讨论：[Discord](https://discord.link-et.link)

---

## 开源协议

MIT License  
可任意修改、再发布，请注明原作者。
