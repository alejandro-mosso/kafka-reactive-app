package com.stock.exchange.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockPreview {
    private String[] parts;
    private String original;

    public boolean isValid() {
        return this.parts.length == 12 &&
                isInteger(parts[0]) && isDouble(parts[3]) &&
                isDouble(parts[4]) && isDouble(parts[5]) &&
                isDouble(parts[6]) && isDouble(parts[7]) &&
                isDouble(parts[8]) && isDouble(parts[9]) &&
                isDouble(parts[10]) && isDouble(parts[11]) &&
                Integer.parseInt(parts[0]) > 0;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
