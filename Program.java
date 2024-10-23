/**
 * Klasa reprezentująca punkt w przestrzeni 2D.
 */
class Punkt {
    double x; // Współrzędna X punktu.
    double y; // Współrzędna Y punktu.

    /**
     * Konstruktor punktu.
     *
     * @param x Współrzędna X punktu.
     * @param y Współrzędna Y punktu.
     */
    Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Zwraca reprezentację tekstową punktu.
     *
     * @return Reprezentacja tekstowa punktu.
     */
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }

    /**
     * Przesuwa punkt o zadane wartości.
     *
     * @param dx Wartość przesunięcia w kierunku X.
     * @param dy Wartość przesunięcia w kierunku Y.
     */
    void przesun(double dx, double dy) {
        this.x = x + dx;
        this.y = y + dy;
    }
}

/**
 * Klasa reprezentująca okrąg w przestrzeni 2D.
 */
class Okrag {
    double promien; // Promień okręgu.
    Punkt srodek; // Środek okręgu.

    /**
     * Konstruktor okręgu z domyślnym środkiem w (0,0).
     *
     * @param promien Promień okręgu.
     */
    Okrag(double promien) {
        this.promien = promien;
        this.srodek = new Punkt(0, 0);
    }

    /**
     * Konstruktor okręgu z określonym środkiem.
     *
     * @param promien Promień okręgu.
     * @param srodek Środek okręgu.
     */
    Okrag(double promien, Punkt srodek) {
        this.promien = promien;
        this.srodek = srodek;
    }

    /**
     * Zwraca reprezentację tekstową okręgu.
     *
     * @return Reprezentacja tekstowa okręgu.
     */
    public String toString() {
        return "[promien: " + promien + " ]" + " środek: " + srodek.toString();
    }

    /**
     * Oblicza pole okręgu.
     *
     * @return Pole okręgu.
     */
    double Pole() {
        return 3.14 * promien * promien;
    }

    /**
     * Przesuwa środek okręgu o zadane wartości.
     *
     * @param u Wartość przesunięcia w kierunku X.
     * @param v Wartość przesunięcia w kierunku Y.
     */
    void przesun(double u, double v) {
        this.srodek.przesun(u, v);
    }

    /**
     * Sprawdza, czy okrąg zawiera dany punkt.
     *
     * @param obj Punkt do sprawdzenia.
     * @return true, jeśli okrąg zawiera punkt, false w przeciwnym razie.
     */
    boolean zawiera(Punkt obj) {
        double d = Math.sqrt(Math.pow(obj.x - srodek.x, 2) + Math.pow(obj.y - srodek.y, 2));
        return d <= promien;
    }

    /**
     * Sprawdza, czy dwa okręgi się przecinają.
     *
     * @param obj Drugi okrąg do sprawdzenia.
     * @return true, jeśli okręgi się przecinają, false w przeciwnym razie.
     */
    boolean przecina(Okrag obj) {
        double d = Math.sqrt(Math.pow(obj.srodek.x - srodek.x, 2) + Math.pow(obj.srodek.y - srodek.y, 2));
        return d > Math.abs(obj.promien - promien) && d < (promien + obj.promien);
    }
}

/**
 * Klasa reprezentująca prostokąt w przestrzeni 2D.
 */
class Prostokat {
    double dlugosc; // Długość prostokąta.
    double szerokosc; // Szerokość prostokąta.
    Punkt wierzcholek; // Wierzchołek prostokąta.

    /**
     * Konstruktor prostokąta z domyślnym wierzchołkiem w (0,0).
     *
     * @param dlugosc Długość prostokąta.
     * @param szerokosc Szerokość prostokąta.
     */
    Prostokat(double dlugosc, double szerokosc) {
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wierzcholek = new Punkt(0, 0);
    }

    /**
     * Konstruktor prostokąta z określonym wierzchołkiem.
     *
     * @param dlugosc Długość prostokąta.
     * @param szerokosc Szerokość prostokąta.
     * @param wierzcholek Wierzchołek prostokąta.
     */
    Prostokat(double dlugosc, double szerokosc, Punkt wierzcholek) {
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wierzcholek = wierzcholek;
    }

    /**
     * Zwraca reprezentację tekstową prostokąta.
     *
     * @return Reprezentacja tekstowa prostokąta.
     */
    public String toString() {
        return "[dl: " + dlugosc + ", sz: " + szerokosc + "]" + wierzcholek.toString();
    }

    /**
     * Oblicza pole prostokąta.
     *
     * @return Pole prostokąta.
     */
    double pole() {
        return dlugosc * szerokosc;
    }

    /**
     * Przesuwa prostokąt o zadane wartości.
     *
     * @param u Wartość przesunięcia w kierunku X.
     * @param v Wartość przesunięcia w kierunku Y.
     */
    void przesun(double u, double v) {
        this.wierzcholek.przesun(u, v);
    }

    /**
     * Sprawdza, czy prostokąt zawiera dany punkt.
     *
     * @param obj Punkt do sprawdzenia.
     * @return true, jeśli prostokąt zawiera punkt, false w przeciwnym razie.
     */
    boolean zawiera(Punkt obj) {
        boolean w1;
        boolean w2;
        if ((wierzcholek.x - szerokosc / 2) <= obj.x && obj.x <= (wierzcholek.x + szerokosc / 2)) {
            w1 = true;
        } else {
            w1 = false;
        }
        if ((wierzcholek.y - dlugosc / 2) <= obj.y && obj.y <= (wierzcholek.y + dlugosc / 2)) {
            w2 = true;
        } else {
            w2 = false;
        }
        return w1 && w2;
    }

    /**
     * Sprawdza, czy prostokąt przecina się z okręgiem.
     *
     * @param obj Okrąg do sprawdzenia.
     * @return true, jeśli prostokąt przecina się z okręgiem, false w przeciwnym razie.
     */
    boolean przecina(Okrag obj) {
        Punkt cw1 = new Punkt(wierzcholek.x + szerokosc / 2, wierzcholek.y + dlugosc / 2);
        Punkt cw2 = new Punkt(wierzcholek.x - szerokosc / 2, wierzcholek.y + dlugosc / 2);
        Punkt cw3 = new Punkt(wierzcholek.x - szerokosc / 2, wierzcholek.y - dlugosc / 2);
        Punkt cw4 = new Punkt(wierzcholek.x + szerokosc / 2, wierzcholek.y - dlugosc / 2);

        double najblizsze_X = Math.max(cw2.x, Math.min(obj.srodek.x, cw1.x));
        double najblizsze_Y = Math.max(cw3.y, Math.min(obj.srodek.y, cw1.y));

        double dystans = Math.sqrt(Math.pow(najblizsze_X - obj.srodek.x, 2) + Math.pow(najblizsze_Y - obj.srodek.y, 2));

        return dystans <= obj.promien;
    }
}

/**
 * Klasa główna programu.
 */
public class Program {
    /**
     * Metoda główna.
     *
     * @param args Argumenty wiersza poleceń.
     */
    public static void main(String[] args) {
        Punkt p1 = new Punkt(3, 3);
        Punkt p2 = new Punkt(6, 6);
        Punkt p3 = new Punkt(10, 10);

        Okrag okrag1 = new Okrag(3, p1);
        Prostokat prostokat = new Prostokat(4, 2, p2);
        Okrag okrag2 = new Okrag(2, p3);

        System.out.println(prostokat.przecina(okrag1));
        System.out.println(prostokat.przecina(okrag2));
    }
}
