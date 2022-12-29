import java.util.ArrayList;

public class Handler {
    public Handler(Field[][] fieldArray) {
        this.fieldArray = fieldArray;
        WhiteMove = true;
        BlackMove = false;
    }

    private Field[][] fieldArray;
    private boolean WhiteMove;
    private boolean WhiteTake;
    private int xWhite;
    private int yWhite;
    private boolean BlackMove;
    private boolean BlackTake;
    private int xBlack;
    private int yBlack;

    public void clickHandler(int x, int y) {
        if (WhiteMove) {
            System.out.println("Ход белых");
            if (WhiteTake) {
                if (availableWhite(x, y)) {
                    xWhite = x;
                    yWhite = y;
                }
                else {
                    if (availableFieldWhite(x, y)) {
                        if (eatingWhite(x, y)) {
                            moveWhite(x, y);
                            if (continueEatingWhite()) {
                                return;
                            } else {
                                WhiteTake = false;
                                WhiteMove = false;
                                BlackMove = true;
                            }
                        } else {
                            moveWhite(x, y);
                            WhiteTake = false;
                            WhiteMove = false;
                            BlackMove = true;
                        }
                    } else {
                        return;
                    }
                }
            } else {
                if (availableWhite(x, y)) {
                    WhiteTake = true;
                    xWhite = x;
                    yWhite = y;
                }
            }
        } else {
            if (BlackTake) {
                System.out.println("Ход черных");
                if (availableBlack(x, y)) {
                    xBlack = x;
                    yBlack = y;
                } else {
                    if (availableFieldBlack(x, y)) {
                        if (eatingBlack(x, y)) {
                            moveBlack(x, y);
                            if (continueEatingBlack()) {
                                return;
                            } else {
                                BlackTake = false;
                                BlackMove = false;
                                WhiteMove = true;
                            }
                        } else {
                            moveBlack(x, y);
                            BlackTake = false;
                            BlackMove = false;
                            WhiteMove = true;
                        }
                    } else {
                        return;
                    }
                }
            } else {
                if (availableBlack(x, y)) {
                    BlackTake = true;
                    xBlack = x;
                    yBlack = y;
                }
            }
        }
    }


    private boolean availableWhite(int x, int y) {
        ArrayList<Pair> eating = new ArrayList<Pair>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fieldArray[i][j].White) {
                    if ((i - 1) >= 0 && (j - 1) >= 0) {
                        if (fieldArray[i - 1][j - 1].Black || fieldArray[i - 1][j - 1].BlackQueen) {
                            if ((i - 2) >= 0 && (j - 2) >= 0) {
                                if (fieldArray[i - 2][j - 2].Empty) {
                                    eating.add(new Pair(i, j));
                                }
                            }
                        }
                    }
                    if ((i - 1) >= 0 && (j + 1) < 8) {
                        if (fieldArray[i - 1][j + 1].Black || fieldArray[i - 1][j + 1].BlackQueen) {
                            if ((i - 2) >= 0 && (j + 2) < 8) {
                                if (fieldArray[i - 2][j + 2].Empty) {
                                    eating.add(new Pair(i, j));
                                }
                            }
                        }
                    }
                }
                if (fieldArray[i][j].WhiteQueen) {
                    //лево верх
                    if ((i - 1) >= 0 && (j - 1) >= 0 && (fieldArray[i - 1][j - 1].Black || fieldArray[i - 1][j - 1].BlackQueen)) {
                        if ((i - 2) >= 0 && (j - 2) >= 0 && fieldArray[i - 2][j - 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                    //право верх
                    if ((i - 1) >= 0 && (j + 1) < 8 && (fieldArray[i - 1][j + 1].Black || fieldArray[i - 1][j + 1].BlackQueen)) {
                        if ((i - 2) >= 0 && (j + 2) < 8 && fieldArray[i - 2][j + 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                    //низ лево
                    if ((i + 1) < 8 && (j - 1) >= 0 && (fieldArray[i + 1][j - 1].Black || fieldArray[i + 1][j - 1].BlackQueen)) {
                        if ((i + 2) < 8 && (j - 2) >= 0 && fieldArray[i + 2][j - 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                    //низ право
                    if ((i + 1) < 8 && (j + 1) < 8 && (fieldArray[i + 1][j + 1].Black || fieldArray[i + 1][j + 1].BlackQueen)) {
                        if ((i + 2) < 8 && (j + 2) < 8 && fieldArray[i + 2][j + 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                }
            }
        }

        if (eating.size() > 0) {
            for (int i = 0; i < eating.size(); i++) {
                if (eating.get(i).x == x && eating.get(i).y == y) {
                    clearCurrent();
                    fieldArray[x][y].Current = true;
                    return true;
                }
            }
        } else if (fieldArray[x][y].White && (x - 1) >= 0 && (y - 1) >= 0 && fieldArray[x - 1][y - 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].White && (x - 1) >= 0 && (y + 1) < 8 && fieldArray[x - 1][y + 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].WhiteQueen && (x - 1) >= 0 && (y - 1) >= 0 && fieldArray[x - 1][y - 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].WhiteQueen && (x - 1) >= 0 && (y + 1) < 8 && fieldArray[x - 1][y + 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].WhiteQueen && (x + 1) < 8 && (y - 1) >= 0 && fieldArray[x + 1][y - 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].WhiteQueen && (x + 1) < 8 && (y + 1) < 8 && fieldArray[x + 1][y + 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        }
        return false;
    }

    private boolean availableFieldWhite(int x, int y) {
        boolean key = false;
        if ((fieldArray[xWhite][yWhite].WhiteQueen || fieldArray[xWhite][yWhite].White) && (xWhite - 2) >= 0 && (yWhite - 2) >= 0 && fieldArray[xWhite - 2][yWhite - 2].Empty && (xWhite - 1) >= 0 && (yWhite - 1) >= 0 && (fieldArray[xWhite - 1][yWhite - 1].Black || fieldArray[xWhite - 1][yWhite - 1].BlackQueen)) {
            key = true;
            if (x == (xWhite - 2) && y == (yWhite - 2)) return true;
        }
        if ((fieldArray[xWhite][yWhite].WhiteQueen || fieldArray[xWhite][yWhite].White) && (xWhite - 2) >= 0 && (yWhite + 2) < 8 && fieldArray[xWhite - 2][yWhite + 2].Empty && (xWhite - 1) >= 0 && (yWhite + 1) < 8 && (fieldArray[xWhite - 1][yWhite + 1].Black || fieldArray[xWhite - 1][yWhite + 1].BlackQueen)) {
            key = true;
            if (x == (xWhite - 2) && y == (yWhite + 2)) return true;
        }

        if (fieldArray[xWhite][yWhite].WhiteQueen && (xWhite + 2) < 8 && (yWhite - 2) >= 0 && fieldArray[xWhite + 2][yWhite - 2].Empty && (xWhite + 1) < 8 && (yWhite - 1) >= 0 && (fieldArray[xWhite + 1][yWhite - 1].Black || fieldArray[xWhite + 1][yWhite - 1].BlackQueen)) {
            key = true;
            if (x == (xWhite + 2) && y == (yWhite - 2)) return true;
        }
        if (fieldArray[xWhite][yWhite].WhiteQueen && (xWhite + 2) < 8 && (yWhite + 2) < 8 && fieldArray[xWhite + 2][yWhite + 2].Empty && (xWhite + 1) < 8 && (yWhite + 1) < 8 && (fieldArray[xWhite + 1][yWhite + 1].Black || fieldArray[xWhite + 1][yWhite + 1].BlackQueen)) {
            key = true;
            if (x == (xWhite + 2) && y == (yWhite + 2)) return true;
        }

        if (!key) {
            if ((fieldArray[x][y].Empty && x == (xWhite - 1) && y == (yWhite - 1)) || (fieldArray[x][y].Empty && x == (xWhite - 1) && y == (yWhite + 1))) {
                return true;
            }

            if ((fieldArray[xWhite][yWhite].WhiteQueen && fieldArray[x][y].Empty && x == (xWhite - 1) && y == (yWhite - 1)) || (fieldArray[xWhite][yWhite].WhiteQueen && fieldArray[x][y].Empty && x == (xWhite - 1) && y == (yWhite + 1)) || (fieldArray[xWhite][yWhite].WhiteQueen && fieldArray[x][y].Empty && x == (xWhite + 1) && y == (yWhite + 1)) || (fieldArray[xWhite][yWhite].WhiteQueen && fieldArray[x][y].Empty && x == (xWhite + 1) && y == (yWhite - 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean eatingWhite(int x, int y) {
        if (x == (xWhite - 2) && y == (yWhite - 2) && fieldArray[x][y].Empty && (fieldArray[xWhite - 1][yWhite - 1].Black || fieldArray[xWhite - 1][yWhite - 1].BlackQueen)) {
            if (fieldArray[xWhite - 1][yWhite - 1].Black) fieldArray[xWhite - 1][yWhite - 1].Black = false;
            else fieldArray[xWhite - 1][yWhite - 1].BlackQueen = false;
            fieldArray[xWhite - 1][yWhite - 1].Empty = true;
            return true;
        }
        if (x == (xWhite - 2) && y == (yWhite + 2) && fieldArray[x][y].Empty && (fieldArray[xWhite - 1][yWhite + 1].Black || fieldArray[xWhite - 1][yWhite + 1].BlackQueen)) {
            if (fieldArray[xWhite - 1][yWhite + 1].Black) fieldArray[xWhite - 1][yWhite + 1].Black = false;
            else fieldArray[xWhite - 1][yWhite + 1].BlackQueen = false;
            fieldArray[xWhite - 1][yWhite + 1].Empty = true;
            return true;
        }

        if (fieldArray[xWhite][yWhite].WhiteQueen && x == (xWhite + 2) && y == (yWhite - 2) && fieldArray[x][y].Empty && (fieldArray[xWhite + 1][yWhite - 1].Black || fieldArray[xWhite + 1][yWhite - 1].BlackQueen)) {
            if (fieldArray[xWhite + 1][yWhite - 1].Black) fieldArray[xWhite + 1][yWhite - 1].Black = false;
            else fieldArray[xWhite + 1][yWhite - 1].BlackQueen = false;
            fieldArray[xWhite + 1][yWhite - 1].Empty = true;
            return true;
        }
        if (fieldArray[xWhite][yWhite].WhiteQueen && x == (xWhite + 2) && y == (yWhite + 2) && fieldArray[x][y].Empty && (fieldArray[xWhite + 1][yWhite + 1].Black || fieldArray[xWhite + 1][yWhite + 1].BlackQueen)) {
            if (fieldArray[xWhite + 1][yWhite + 1].Black) fieldArray[xWhite + 1][yWhite + 1].Black = false;
            else fieldArray[xWhite + 1][yWhite + 1].BlackQueen = false;
            fieldArray[xWhite + 1][yWhite + 1].Empty = true;
            return true;
        }
        return false;
    }

    private void moveWhite(int x, int y) {

        if (fieldArray[xWhite][yWhite].White) {
            if (x == 0) {
                fieldArray[x][y].WhiteQueen = true;
                fieldArray[x][y].Empty = false;
                fieldArray[xWhite][yWhite].Empty = true;
                fieldArray[xWhite][yWhite].White = false;
            } else {
                fieldArray[x][y].White = true;
                fieldArray[x][y].Empty = false;
                fieldArray[xWhite][yWhite].Empty = true;
                fieldArray[xWhite][yWhite].White = false;
            }
        } else {
            fieldArray[x][y].WhiteQueen = true;
            fieldArray[x][y].Empty = false;
            fieldArray[xWhite][yWhite].Empty = true;
            fieldArray[xWhite][yWhite].WhiteQueen = false;
        }
        xWhite = x;
        yWhite = y;
    }

    private boolean continueEatingWhite() {
        if ((xWhite - 2) >= 0 && (yWhite - 2) >= 0 && fieldArray[xWhite - 2][yWhite - 2].Empty && fieldArray[xWhite - 1][yWhite - 1].Black) {
            return true;
        }
        if ((xWhite - 2) >= 0 && (yWhite + 2) < 8 && fieldArray[xWhite - 2][yWhite + 2].Empty && fieldArray[xWhite - 1][yWhite + 1].Black) {
            return true;
        }

        if (fieldArray[xWhite][yWhite].WhiteQueen && (xWhite + 2) < 8 && (yWhite - 2) >= 0 && fieldArray[xWhite + 2][yWhite - 2].Empty && (fieldArray[xWhite + 1][yWhite - 1].Black || fieldArray[xWhite + 1][yWhite - 1].BlackQueen)) {
            return true;
        }
        if (fieldArray[xWhite][yWhite].WhiteQueen && (xWhite + 2) < 8 && (yWhite + 2) < 8 && fieldArray[xWhite + 2][yWhite + 2].Empty && (fieldArray[xWhite + 1][yWhite + 1].Black || fieldArray[xWhite + 1][yWhite + 1].BlackQueen)) {
            return true;
        }
        return false;
    }

    private boolean availableBlack(int x, int y) {
        ArrayList<Pair> eating = new ArrayList<Pair>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fieldArray[i][j].Black) {
                    if ((i + 1) < 8 && (j - 1) >= 0) {
                        if (fieldArray[i + 1][j - 1].White || fieldArray[i + 1][j - 1].WhiteQueen) {
                            if ((i + 2) < 8 && (j - 2) >= 0) {
                                if (fieldArray[i + 2][j - 2].Empty) {
                                    eating.add(new Pair(i, j));
                                }
                            }
                        }
                    }
                    if ((i + 1) < 8 && (j + 1) < 8) {
                        if (fieldArray[i + 1][j + 1].White || fieldArray[i + 1][j + 1].WhiteQueen) {
                            if ((i + 2) < 8 && (j + 2) < 8) {
                                if (fieldArray[i + 2][j + 2].Empty) {
                                    eating.add(new Pair(i, j));
                                }
                            }
                        }
                    }
                }
                if (fieldArray[i][j].BlackQueen) {
                    //лево верх
                    if ((i - 1) >= 0 && (j - 1) >= 0 && (fieldArray[i - 1][j - 1].White || fieldArray[i - 1][j - 1].WhiteQueen)) {
                        if ((i - 2) >= 0 && (j - 2) >= 0 && fieldArray[i - 2][j - 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                    //право верх
                    if ((i - 1) >= 0 && (j + 1) < 8 && (fieldArray[i - 1][j + 1].White || fieldArray[i - 1][j + 1].WhiteQueen)) {
                        if ((i - 2) >= 0 && (j + 2) < 8 && fieldArray[i - 2][j + 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                    //низ лево
                    if ((i + 1) < 8 && (j - 1) >= 0 && (fieldArray[i + 1][j - 1].White || fieldArray[i + 1][j - 1].WhiteQueen)) {
                        if ((i + 2) < 8 && (j - 2) >= 0 && fieldArray[i + 2][j - 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                    //низ право
                    if ((i + 1) < 8 && (j + 1) < 8 && (fieldArray[i + 1][j + 1].White || fieldArray[i + 1][j + 1].WhiteQueen)) {
                        if ((i + 2) < 8 && (j + 2) < 8 && fieldArray[i + 2][j + 2].Empty) {
                            eating.add(new Pair(i, j));
                        }
                    }
                }
            }
        }

        if (eating.size() > 0) {
            for (int i = 0; i < eating.size(); i++) {
                if (eating.get(i).x == x && eating.get(i).y == y) {
                    clearCurrent();
                    fieldArray[x][y].Current = true;
                    return true;
                }
            }
        } else if (fieldArray[x][y].Black && (x + 1) < 8 && (y - 1) >= 0 && fieldArray[x + 1][y - 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].Black && (x + 1) < 8 && (y + 1) < 8 && fieldArray[x + 1][y + 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].BlackQueen && (x - 1) >= 0 && (y - 1) >= 0 && fieldArray[x - 1][y - 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].BlackQueen && (x - 1) >= 0 && (y + 1) < 8 && fieldArray[x - 1][y + 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].BlackQueen && (x + 1) < 8 && (y - 1) >= 0 && fieldArray[x + 1][y - 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        } else if (fieldArray[x][y].BlackQueen && (x + 1) < 8 && (y + 1) < 8 && fieldArray[x + 1][y + 1].Empty) {
            clearCurrent();
            fieldArray[x][y].Current = true;
            return true;
        }
        return false;
    }

    private boolean availableFieldBlack(int x, int y) {
        boolean key = false;
        if ((fieldArray[xBlack][yBlack].BlackQueen || fieldArray[xBlack][yBlack].Black) && (xBlack + 2) < 8 && (yBlack - 2) >= 0 && fieldArray[xBlack + 2][yBlack - 2].Empty && (xBlack + 1) < 8 && (yBlack - 1) >= 0 && (fieldArray[xBlack + 1][yBlack - 1].White || fieldArray[xBlack + 1][yBlack - 1].WhiteQueen)) {
            key = true;
            if (x == (xBlack + 2) && y == (yBlack - 2)) return true;
        }
        if ((fieldArray[xBlack][yBlack].BlackQueen || fieldArray[xBlack][yBlack].Black) && (xBlack + 2) < 8 && (yBlack + 2) < 8 && fieldArray[xBlack + 2][yBlack + 2].Empty && (xBlack + 1) < 8 && (yBlack + 1) < 8 && (fieldArray[xBlack + 1][yBlack + 1].White || fieldArray[xBlack + 1][yBlack + 1].WhiteQueen)) {
            key = true;
            if (x == (xBlack + 2) && y == (yBlack + 2)) return true;
        }

        if (fieldArray[xBlack][yBlack].BlackQueen && (xBlack - 2) >= 0 && (yBlack - 2) >= 0 && fieldArray[xBlack - 2][yBlack - 2].Empty && (xBlack - 1) >= 0 && (yBlack - 1) >= 0 && (fieldArray[xBlack - 1][yBlack - 1].White || fieldArray[xBlack - 1][yBlack - 1].WhiteQueen)) {
            key = true;
            if (x == (xBlack - 2) && y == (yBlack - 2)) return true;
        }
        if (fieldArray[xBlack][yBlack].BlackQueen && (xBlack - 2) >= 0 && (yBlack + 2) < 8 && fieldArray[xBlack - 2][yBlack + 2].Empty && (xBlack - 1) >= 0 && (yBlack + 1) < 8 && (fieldArray[xBlack - 1][yBlack + 1].White || fieldArray[xBlack - 1][yBlack + 1].WhiteQueen)) {
            key = true;
            if (x == (xBlack - 2) && y == (yBlack + 2)) return true;
        }

        if (!key) {
            if ((fieldArray[x][y].Empty && x == (xBlack + 1) && y == (yBlack - 1)) || (fieldArray[x][y].Empty && x == (xBlack + 1) && y == (yBlack + 1))) {
                return true;
            }

            if ((fieldArray[xBlack][yBlack].BlackQueen && fieldArray[x][y].Empty && x == (xBlack - 1) && y == (yBlack - 1)) || (fieldArray[xBlack][yBlack].BlackQueen && fieldArray[x][y].Empty && x == (xBlack - 1) && y == (yBlack + 1)) || (fieldArray[xBlack][yBlack].BlackQueen && fieldArray[x][y].Empty && x == (xBlack + 1) && y == (yBlack + 1)) || (fieldArray[xBlack][yBlack].BlackQueen && fieldArray[x][y].Empty && x == (xBlack + 1) && y == (yBlack - 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean eatingBlack(int x, int y) {
        if ((fieldArray[xBlack][yBlack].BlackQueen || fieldArray[xBlack][yBlack].Black) && x == (xBlack + 2) && y == (yBlack - 2) && fieldArray[x][y].Empty && (fieldArray[xBlack + 1][yBlack - 1].White || fieldArray[xBlack + 1][yBlack - 1].WhiteQueen)) {
            if (fieldArray[xBlack + 1][yBlack - 1].White) fieldArray[xBlack + 1][yBlack - 1].White = false;
            else fieldArray[xBlack + 1][yBlack - 1].WhiteQueen = false;
            fieldArray[xBlack + 1][yBlack - 1].Empty = true;
            return true;
        }
        if ((fieldArray[xBlack][yBlack].BlackQueen || fieldArray[xBlack][yBlack].Black) && x == (xBlack + 2) && y == (yBlack + 2) && fieldArray[x][y].Empty && (fieldArray[xBlack + 1][yBlack + 1].White || fieldArray[xBlack + 1][yBlack + 1].WhiteQueen)) {
            if (fieldArray[xBlack + 1][yBlack + 1].White) fieldArray[xBlack + 1][yBlack + 1].White = false;
            else fieldArray[xBlack + 1][yBlack + 1].WhiteQueen = false;
            fieldArray[xBlack + 1][yBlack + 1].Empty = true;
            return true;
        }

        if (fieldArray[xBlack][yBlack].BlackQueen && x == (xBlack - 2) && y == (yBlack - 2) && fieldArray[x][y].Empty && (fieldArray[xBlack - 1][yBlack - 1].White || fieldArray[xBlack - 1][yBlack - 1].WhiteQueen)) {
            if (fieldArray[xBlack - 1][yBlack - 1].White) fieldArray[xBlack - 1][yBlack - 1].White = false;
            else fieldArray[xBlack - 1][yBlack - 1].WhiteQueen = false;
            fieldArray[xBlack - 1][yBlack - 1].Empty = true;
            return true;
        }
        if (fieldArray[xBlack][yBlack].BlackQueen && x == (xBlack - 2) && y == (yBlack + 2) && fieldArray[x][y].Empty && (fieldArray[xBlack - 1][yBlack + 1].White || fieldArray[xBlack - 1][yBlack + 1].WhiteQueen)) {
            if (fieldArray[xBlack - 1][yBlack + 1].White) fieldArray[xBlack - 1][yBlack + 1].White = false;
            else fieldArray[xBlack - 1][yBlack + 1].WhiteQueen = false;
            fieldArray[xBlack - 1][yBlack + 1].Empty = true;
            return true;
        }
        return false;
    }

    private void moveBlack(int x, int y) {

        if (fieldArray[xBlack][yBlack].Black) {
            if (x == 7) {
                fieldArray[x][y].BlackQueen = true;
                fieldArray[x][y].Empty = false;
                fieldArray[xBlack][yBlack].Empty = true;
                fieldArray[xBlack][yBlack].Black = false;
            } else {
                fieldArray[x][y].Black = true;
                fieldArray[x][y].Empty = false;
                fieldArray[xBlack][yBlack].Empty = true;
                fieldArray[xBlack][yBlack].Black = false;
            }
        } else {
            fieldArray[x][y].BlackQueen = true;
            fieldArray[x][y].Empty = false;
            fieldArray[xBlack][yBlack].Empty = true;
            fieldArray[xBlack][yBlack].BlackQueen = false;
        }
        xBlack = x;
        yBlack = y;
    }

    private boolean continueEatingBlack() {
        if ((xBlack + 2) < 8 && (yBlack - 2) >= 0 && fieldArray[xBlack + 2][yBlack - 2].Empty && fieldArray[xBlack + 1][yBlack - 1].White) {
            return true;
        }
        if ((xBlack + 2) < 8 && (yBlack + 2) < 8 && fieldArray[xBlack + 2][yBlack + 2].Empty && fieldArray[xBlack + 1][yBlack + 1].White) {
            return true;
        }
        if (fieldArray[xBlack][yBlack].BlackQueen && (xBlack - 2) >= 0 && (yBlack - 2) >= 0 && fieldArray[xBlack - 2][yBlack - 2].Empty && (fieldArray[xBlack - 1][yBlack - 1].White || fieldArray[xBlack - 1][yBlack - 1].WhiteQueen)) {
            return true;
        }
        if (fieldArray[xBlack][yBlack].BlackQueen && (xBlack - 2) >= 0 && (yBlack + 2) < 8 && fieldArray[xBlack - 2][yBlack + 2].Empty && (fieldArray[xBlack - 1][yBlack + 1].White && fieldArray[xBlack - 1][yBlack + 1].WhiteQueen)) {
            return true;
        }
        return false;
    }

    private void clearCurrent() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fieldArray[i][j].Current = false;
            }
        }
    }

    private class Pair {
        public Pair() {
        }

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x;
        public int y;
    }
}
