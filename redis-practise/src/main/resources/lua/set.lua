---@diagnostic disable: undefined-global
local key = KEYS[1]
local value = ARGV[1]
local owner = ARGV[2]
local lockOwner = redis.call('HGET', key, 'OWNER')
local lockInfo = redis.call('HGET', key, 'LOCK_INFO')
if lockOwner and lockOwner == owner then
    redis.call('HMSET', key, 'LOCK_INFO', 'unlocked', 'VALUE', value)
    redis.call('HDEL', key, 'UNLOCK_TIME')
    return 0
end
return 1
