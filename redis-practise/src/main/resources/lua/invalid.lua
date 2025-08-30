---@diagnostic disable: undefined-global
local key = KEYS[1]
local newUnlockTime = tonumber(ARGV[1])
redis.call('HDEL', key, 'OWNER')
local value = redis.call('HGET', key, 'VALUE')
redis.call('HSET', key, 'LOCK_INFO', 'locked')
if not value or value == '' then
      return {true, 'EMPTY_VALUE_SUCCESS'}
end
if newUnlockTime > 0 then
      redis.call('HSET', key, 'UNLOCK_TIME', newUnlockTime)
end
return {'', 'SUCCESS'}
