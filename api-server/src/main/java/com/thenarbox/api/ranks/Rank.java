package com.thenarbox.api.ranks;

import org.bukkit.event.Listener;

public class Rank implements Listener {

    public static String getRankPriority(String rank){
        return switch (rank) {
            case "Majitel" ->
                    "A";
            case "Vedení" ->
                    "B";
            case "V.Developer" ->
                    "C";
            case "Developer" ->
                    "D";
            case "V.Helper" ->
                    "E";
            case "Helper" ->
                    "F";
            case "V.Builder" ->
                    "G";
            case "Builder" ->
                    "H";
            case "Eventer" ->
                    "I";
            case "Sponzor+" ->
                    "J";
            case "Sponzor" ->
                    "K";
            case "VIP" ->
                    "L";
            case "Podporovatel" ->
                    "N";
            case "Hráč" ->
                    "N";
            default -> "";
        };
    }

    public static String getRankPrefix(String rank){
        return switch (rank) {
            case "Majitel" ->
                    "&x&f&b&0&9&0&9&lM&x&f&b&1&b&1&b&lA&x&f&c&2&c&2&c&lJ&x&f&c&3&e&3&e&lI&x&f&c&5&0&5&0&lT&x&f&d&6&1&6&1&lE&x&f&d&7&3&7&3&lL";
            case "Vedení" ->
                    "&x&f&f&0&1&0&1&lV&x&e&b&0&3&0&8&lE&x&d&7&0&5&0&f&lD&x&c&3&0&6&1&6&lE&x&a&f&0&8&1&d&lN&x&9&b&0&a&2&4&lÍ";
            case "V.Developer" ->
                    "&x&f&b&a&c&1&3&lD&x&f&b&a&0&1&a&lE&x&f&c&9&3&2&0&lV&x&f&c&8&7&2&7&lE&x&f&c&7&a&2&e&lL&x&f&c&6&e&3&4&lO&x&f&d&6&1&3&b&lP&x&f&d&5&5&4&1&lE&x&f&d&4&8&4&8&lR";
            case "Developer" ->
                    "&x&d&c&f&b&1&d&lD&x&e&0&f&3&2&7&lE&x&e&4&e&b&3&0&lV&x&e&8&e&2&3&a&lE&x&e&d&d&a&4&3&lL&x&f&1&d&2&4&d&lO&x&f&5&c&a&5&6&lP&x&f&9&c&1&6&0&lE&x&f&d&b&9&6&9&lR";
            case "V.Helper" ->
                    "&x&3&d&8&1&4&8&lH&x&3&1&7&8&3&d&lE&x&2&5&6&f&3&3&lL&x&1&8&6&5&2&8&lP&x&0&c&5&c&1&e&lE&x&0&0&5&3&1&3&lR";
            case "Helper" ->
                    "&x&1&5&9&5&0&0&lH&x&2&f&a&a&0&a&lE&x&4&9&b&f&1&5&lL&x&6&4&d&3&1&f&lP&x&7&e&e&8&2&a&lE&x&9&8&f&d&3&4&lR";
            case "V.Builder" ->
                    "&x&8&2&0&8&9&5&lB&x&7&f&0&a&a&6&lU&x&7&b&0&c&b&8&lI&x&7&8&0&f&c&9&lL&x&7&4&1&1&d&a&lD&x&7&1&1&3&e&c&lE&x&6&d&1&5&f&d&lR";
            case "Builder" ->
                    "&x&f&b&0&e&f&2&lB&x&e&d&1&5&f&4&lU&x&d&f&1&c&f&6&lI&x&d&1&2&4&f&8&lL&x&c&3&2&b&f&9&lD&x&b&5&3&2&f&b&lE&x&a&7&3&9&f&d&lR";
            case "Eventer" ->
                    "&x&f&b&f&b&f&b&lE&x&f&b&f&2&f&b&lV&x&f&c&e&9&f&b&lE&x&f&c&e&0&f&b&lN&x&f&c&d&6&f&b&lT&x&f&d&c&d&f&b&lE&x&f&d&c&4&f&b&lR";
            case "Sponzor+" ->
                    "&x&7&f&e&5&f&bS&x&8&5&e&8&f&2P&x&8&c&e&c&e&9O&x&9&2&e&f&e&0N&x&9&8&f&3&d&8Z&x&9&e&f&6&c&fO&x&a&5&f&a&c&6R&x&a&b&f&d&b&d+";
            case "Sponzor" ->
                    "&x&d&7&e&a&0&6S&x&d&1&d&6&0&7P&x&c&b&c&2&0&8O&x&c&6&a&e&0&9N&x&c&0&9&9&0&aZ&x&b&a&8&5&0&bO&x&b&4&7&1&0&cR";
            case "VIP" ->
                    "&x&d&1&e&a&3&9V&x&b&c&c&f&1&dI&x&a&6&b&4&0&0P";
            case "Podporovatel" ->
                    "";
            default -> "";
        };
    }
}
