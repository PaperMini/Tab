# MiniTab

Simple and **small** (~200LoC) TabList Header/Footer Manager, powered by MiniMessage and packed with features!

## Table of Contents
- [Setup](#setup)
- [Usage](#usage)
  - [PlaceholderAPI](#placeholderapi)
- [Configuration](#configuration)
- [License](#license)

## Setup

> [!IMPORTANT]  
> MiniTab is only compatible with [Paper](https://papermc.io) and Forks, not Bukkit or Spigot!

Download from (https://modrinth.com/projects/tablist), drop into `/plugins` and restart your server

## Usage

MiniTab will automatically load the header/footer from `config.json` and display it to all players.

> [!NOTE]  
> If MiniTab detects an empty Header/Footer, it will not display it!

To change the text, simply edit the [configuration](#configuration) and issue `/tab reload`

### Commands

| Command     | Description                                                  |
|-------------|--------------------------------------------------------------|
| /tab info   | Information about MiniTab                                    |
| /tab reload | Reloads the Config (and rechecks for PlaceholderAPI support) |
| /tab stop   | Suspend MiniTab functions (removes the header/footer)        |
| /tab start  | Resumes MiniTab functions                                    |

### PlaceholderAPI

MiniTab users can use [PlaceholdeAPI](https://wiki.placeholderapi.com/) placeholders in both header and footer. Refer to 
the [PlaceholderAPI documentation](https://wiki.placeholderapi.com/) to discover Placeholders

## Configuration

```json
{
  "version": 1,
  "header": {
    "raw": "<gradient:red:blue:<x>>Hello, %player_name%!</gradient>",
    "animated": true,
    "animationSpeed": 0.1
  },
  "footer": {
    "raw": "<blue><b>Its an nice Day!</b></blue>",
    "animated": false,
    "animationSpeed": 0.0
  }
}
```

`version` -> Used for updates, **do not touch!**  
`header` -> The header, **above** the players  
`footer` -> The footer, **below** the players  
`raw` - The Message. Supports MiniMessage and Placeholders from PlaceholderAPI  
`animated` - If set to true, the plugin will try to animate the text  

> [!IMPORTANT]  
> Animations are created by (ab)using the `power` feature of MiniMessage. If you want to use them, please make
> sure you include the placeholder `<x>` as the last argument of your Gradient. Refer to the
> [MiniMessage Documentation](https://docs.advntr.dev/minimessage/format.html#gradient) to learn more, or have  look at
> the example above.

`animationSpeed` - How fast the animation is. The higher the number - the faster the animation.

## License

As long as not stated otherwise, Mini.Tab is licensed under the GNU Affero General Public License, version 3, which
requires that any modified version of this software, or software using it, whether directly or used over a network
has to have its code available to its users, in addition to the standard GPL requirements for sharing modifications.
