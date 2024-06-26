local logtable = {}

local function logit(msg)
  logtable[#logtable+1] = msg
end


logit("foo")
logit("bar")


local stylerKey = KEYS[2]



-- HGET KEY_NAME FIELD_NAME
-- Redis Hget 命令用于返回哈希表中指定字段的值。
local actualInnerHashKey = stylerKey --也就是82001，子key，key2
local userWantCount=ARGV[1]
logit(userWantCount)
userWantCount=tonumber(userWantCount)
--tonumber tostring
local ticketSeatAvailabilityTokenValue = tonumber(redis.call('hget', KEYS[1], stylerKey)) --hget:获取哈希表字段值
-- print("ticketSeatAvailabilityTokenValue:")
-- print(ticketSeatAvailabilityTokenValue)
-- print("userWantCount:")
-- print(userWantCount)

-- logit(ticketSeatAvailabilityTokenValue)

local temp
if ticketSeatAvailabilityTokenValue < userWantCount  then  --如果实际票《能给的，
    return 1--不能分配
    --logit(1)
else
    temp=0
    --return 0--正常能分配
    --logit(0)
end

--千万不要去把计算的值给数据库
--而是直接--cnt就性
redis.call('hincrby', KEYS[1], stylerKey, -userWantCount)
return 0

--return logtable
