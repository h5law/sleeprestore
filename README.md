# SleepRestore

This is a simple plugin for a Spigot/Paper Minecraft server that will
automatically heal a player (health and food levels) when they trigger a
PlayerDeepSleepEvent (sleep long enough to pass through the night/storm).

When multiple players are on the server the player sleeping will be healed and
a message will be broadcasted to the server giving them the option to cancel
the sleep event by clicking on the `[CANCEL]` text. If nobody cancels the
event 10s after the player began sleeping the world will change to day and
thunder/rain will be stopped. If the event is cancelled the world will stay in
the same state and the player will just be healed.

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
