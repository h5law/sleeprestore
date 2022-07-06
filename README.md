# SleepRestore

This is a simple plugin for a Spigot/Paper Minecraft server that will
automatically heal a player (health and food levels) when they trigger a
PlayerDeepSleepEvent (sleep long enough to pass through the night/storm).

Commands can be used to toggle debug messages and the health restoration
feature itself:

```
/sleeprestore <OPTION> <ARGUMENT>
/sr <OPTION> <ARGUMENT>
```

Options:
 - help (Shows usage)
 - debug
   - true
   - false (Default)
   - NO ARG will toggle
 - restore
   - true (Default)
   - false
   - NO ARG will toggle

## Dependencies

 - Minecraft v1.19
 - Paper v1.19-40

## Bugs

I have only tested this locally on a PaperMC v1.19-40 server. If you find any
bugs please reach out to me and I'll be more than happy to fix them.

If there are any features you want to add reach out to me as well and I can
try to implement them.


## License

[BSD-3](https://github.com/h5law/sleeprestore/blob/main/LICENSE.txt)
