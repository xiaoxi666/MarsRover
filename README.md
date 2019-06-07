
 ## 初始需求拆解:
 
     信息：
     1. 类别划分：区域、初始化位置
        1.1 区域：长X+宽Y
        1.2 初始化：降落坐标（x+y）、朝向（E|S|W|N）
    
     指令：
     1. 指令类别划分：移动、转向
        1.1 移动：前进f|后退b
        1.1 转向：左转90°|右转90°
     2. 指令发送方式：批量发送
    
     火星车：
     1. 执行批量命令
     2. 回报自己所在位置坐标（x+y）、朝向（E|S|W|N）



注意点：
    
     1. 朝向：认为初始化朝向只能是四个方向，不能有复合方向，比如东偏南等
     2. 认为初始化坐标一定位于区域内，不再检查
 
 
 ## 扩展需求拆解：
 
     1. 区域限制：
        1.1 若火星车初始化坐标在区域边界或区域外，则放弃执行任何指令
        1.2 若火星车在移动过程中行驶到区域边界，则放弃后续指令（也就停在原地了）
        1.3 向地球汇报
    
     2. 坐标系循环：
        拒绝该需求，以现有技术水平，火星车活动范围不可能绕火星一圈
        
     3. 遭遇障碍物：
        拒绝该需求，原因：
         3.1 障碍物具体描述未给出，如什么形状？用障碍物边界描述？初始落到障碍物上怎么办？
         3.2 该需求与区域限制需求冲突
        
