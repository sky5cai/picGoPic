# 微信自动发送信息脚本

> 导读：此脚本的原理是控制电脑上的微信客户端，实现用脚本定位窗口(隐藏或者被覆盖也行)，然后用脚本把内容复制到剪切版中，用脚本在定位的窗口中实现ctrl+v,模拟enter发送
>
> 需要用的的python模块：
>
> pywin32 实现模拟按键盘发送，及定位位置
>
> APScheduler 实现定时发送

### 教程

```
https://zhuanlan.zhihu.com/p/28605447
```

### 1. 安装pyhon

步骤：略

### 2.安装pycharm

步骤：略

### 3. pycharm安装模块

![](https://i.loli.net/2020/03/03/fPmrN9RjGtE1vJQ.png)

### 4.新建python类编写脚本

```python
import win32api, win32gui, win32con
import win32clipboard as clipboard
import time
# import requests
from apscheduler.schedulers.blocking import BlockingScheduler


###############################
#  微信发送
###############################


def send_m(win):
    # 以下为“CTRL+V”组合键,回车发送，（方法一）
    win32api.keybd_event(17, 0, 0, 0)  # 有效，按下CTRL
    time.sleep(1)  # 需要延时
    win32gui.SendMessage(win, win32con.WM_KEYDOWN, 86, 0)  # V
    win32api.keybd_event(17, 0, win32con.KEYEVENTF_KEYUP, 0)  # 放开CTRL
    time.sleep(1)  # 缓冲时间
    win32gui.SendMessage(win, win32con.WM_KEYDOWN, win32con.VK_RETURN, 0)  # 回车发送
    return


def txt_ctrl_v(txt_str):
    # 定义文本信息,将信息缓存入剪贴板
    clipboard.OpenClipboard()
    clipboard.EmptyClipboard()
    clipboard.SetClipboardData(win32con.CF_UNICODETEXT, txt_str)
    clipboard.CloseClipboard()
    return


# def day_english():
#     # 获取金山词霸每日一句
#     url = 'http://open.iciba.com/dsapi'
#     r = requests.get(url)
#     content = r.json()['content']
#     note = r.json()['note']
#     print(content + note)
#     return content + note


def get_window(className, titleName):
    title_name = className  # 单独打开，好友名称
    win = win32gui.FindWindow(className, titleName)
    # 窗体前端显示
    # win32gui.SetForegroundWindow(win)
    # 使窗体最大化
    win32gui.ShowWindow(win, win32con.SW_MAXIMIZE)
    win = win32gui.FindWindow(className, titleName)
    print("找到句柄：%x" % win)
    if win != 0:
        left, top, right, bottom = win32gui.GetWindowRect(win)
        print(left, top, right, bottom)  # 最小化为负数
        win32gui.SetForegroundWindow(win)  # 获取控制
        time.sleep(0.5)
    else:
        print('请注意：找不到【%s】这个人（或群），请激活窗口！' % title_name)
    return win


#######################发送过程=================

def sendTaskLog():
    # 查找微信小窗口
    # win = get_window('ChatWnd', '文件传输助手')
    win = get_window('ChatWnd', '产品4.5协作')
    # 读取文本
    file = open(r'F:\tasklog.txt', mode='r', encoding='UTF-8')
    str = file.read()
    print(str)
    txt_ctrl_v(str)
    send_m(win)


scheduler = BlockingScheduler()
# scheduler.add_job(sendTaskLog, 'interval', seconds=3)
# scheduler.add_job(sendTaskLog, 'cron',day_of_week='mon-fri', hour=7,minute=31,second='10',misfire_grace_time=30)
scheduler.add_job(sendTaskLog, 'cron', day_of_week='mon-fri', hour=6, minute=55, second='10', misfire_grace_time=30)

try:
    scheduler.start()
except (KeyboardInterrupt, SystemExit):
    pass

```

其中，定位窗口：

```
win = get_window('ChatWnd', '产品4.5协作')
```

需以这样的形式开启小窗口(隐藏或者小窗口被覆盖也能定位到)，这样就能定位到了
![](https://i.loli.net/2020/03/03/B12ouWMCcQZbYEA.png)