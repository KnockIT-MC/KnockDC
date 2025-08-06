# KnockDC
KnockDC is a Discord integration plugin for KnockIT.

## Installation
Download the plugin jar from [here](https://github.com/keishispl/knockDC/releases/latest) and place it in your plugins folder.

## Configuration
You can configure KnockDC in the `config.yml` file.

```yaml
token: 'your-token-here'
update-check: true

chat:
  enabled: true
  join-leave-messages: true
  channels:
  - 0000000000000000000
  - 1111111111111111111

receive:
  enabled: true
  channels:
  - 0000000000000000000
  - 1111111111111111111
```

### token
Insert your Discord bot token as a string, e.g.
```yaml
token: 'your-token-here'
```

### update-check
Set this to `true` to enable update notifications sent to operators when they join, `false` to disable. e.g.
```yaml
update-check: true
```

### chat.enabled
Set this to `true` to enable sending chat messages to Discord, `false` to disable. e.g.
```yaml
chat:
  enabled: true
```

### chat.join-leave-messages
Requires the `chat.enabled` option to be set to `true`.

Set this to `true` to enable sending join/leave messages to Discord, `false` to disable. e.g.
```yaml
chat:
  enabled: true
  join-leave-messages: true
```

### chat.channels
Insert the IDs of the channels you want to send chat messages to as a list, e.g. 
```yaml
chat:
  channels:
    - 0000000000000000000
    - 1111111111111111111
```

### receive.enabled
Set this to `true` to enable receiving messages from Discord, `false` to disable. e.g.
```yaml
receive:
  enabled: true
```


### receive.channels
Insert the IDs of the channels you want to receive messages from as a list, e.g. 
```yaml
receive:
  channels:
    - 0000000000000000000
    - 1111111111111111111
```