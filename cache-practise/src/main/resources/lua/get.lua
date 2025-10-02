---@diagnostic disable: undefined-global
local key = KEYS[1]
local newUnlockTime = ARGV[1]
local owner = ARGV[2]
local currentTime = tonumber(ARGV[3])
local value = redis.call('HGET', key, 'VALUE')
local unlockTime = redis.call('HGET', key, 'UNLOCK_TIME')
local lockOwner = redis.call('HGET', key, 'OWNER')
local lockInfo = redis.call('HGET', key, 'LOCK_INFO')
if unlockTime and currentTime > tonumber(unlockTime or 0) then
    redis.call('HMSET', key, 'LOCK_INFO', 'locked', 'UNLOCK_TIME', newUnlockTime, 'OWNER', owner)
    return {value, 'NEED_QUERY'}
end
if not value or value == '' then
    if lockOwner and lockOwner ~= owner then
        return {value, 'NEED_WAIT'}
    end
    redis.call('HMSET', key, 'LOCK_INFO', 'locked', 'UNLOCK_TIME', newUnlockTime, 'OWNER', owner)
    return {value, 'NEED_QUERY'}
end
if lockInfo and lockInfo == 'locked' then
    return {value, 'SUCCESS_NEED_QUERY'}
end
return {value , 'SUCCESS'}
