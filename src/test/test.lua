--这个文件除了开头的冒号判定，其他全懂了 --redis里头，index12306-ticket-service:ticket_availability_token_bucket:1这种，存储的两地的某类型的座位个数，是中间所有小点的座位个数在程序最初时刻完全相同。因为你北京到宁波的最终余票不可能要的时候才所有路途全加，这样子太卡了。
--KEY[1] KEY[2]

actualKey = KEY[2]


local jsonArrayStr = ARGV[1] --JSON.toJSONString(seatTypeCountArray)   // [{"seatType":"0","count":"1"}]
local jsonArray = cjson.decode(jsonArrayStr)

for index, jsonObj in ipairs(jsonArray) do --懂了
    local seatType = tonumber(jsonObj.seatType)
    local count = tonumber(jsonObj.count)
    local actualInnerHashKey = actualKey .. "_" .. seatType --actualKey是北京南_杭州东
    local ticketSeatAvailabilityTokenValue = tonumber(redis.call('hget', KEYS[1], tostring(actualInnerHashKey))) --hget:获取哈希表字段值
    if ticketSeatAvailabilityTokenValue < count then  --如果实际票《能给的，
        return 1
    end
end
