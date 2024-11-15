import java.util.Scanner;

public class PendakianSemeru {
    // Definisi peta pendakian (Array 2D)
    static char[][] petaPendakian = {
        {'P', 'P', 'P', 'P', 'P'},
        {'H', 'H', 'H', 'B', 'B'},
        {'H', 'H', 'H', 'B', 'B'},
        {'H', 'H', 'H', 'B', 'B'},
        {'H', 'H', 'H', 'B', 'B'}
    };

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input parameter pendakian
            System.out.print("Masukkan jumlah tenaga awal: ");
            int tenaga = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Masukkan jalur pendakian: ");
            String jalur = scanner.nextLine();

            // Proses pendakian
            prosesPerjalanan(tenaga, jalur);
        }
    }

    static void prosesPerjalanan(int tenaga, String jalur) {
        int posisiX = 0;
        int posisiY = 0;
        int energi = tenaga;
        boolean berhasilSampaiPuncak = false;

        for (int i = 0; i < jalur.length(); i++) {
            char langkah = jalur.charAt(i);

            // Cek apakah langkah valid
            if (!cekLangkahValid(posisiX, posisiY, langkah)) {
                System.out.println("Jalur anda salah, anda masuk ke jurang/blank 45");
                return;
            }

            // Kurangi energi untuk setiap pergerakan
            if (langkah != 'R') {
                energi--;
            }

            // Proses pergerakan
            switch (langkah) {
                case 'L':
                    posisiY--;
                    break;
                case 'R':
                    posisiY++;
                    // Tambah energi jika istirahat
                    energi += 10;
                    break;
                case 'U':
                    posisiX--;
                    break;
                case 'D':
                    posisiX++;
                    break;
            }

            // Cek apakah sudah mencapai puncak
            if (posisiX == 0 && posisiY == 4) {
                berhasilSampaiPuncak = true;
                break;
            }

            // Cek energi
            if (energi <= 0) {
                System.out.println("Jalur anda benar, tapi tenaga anda tidak akan kuat, coba jalur lain atau sempatkan istirahat terlebih dahulu");
                return;
            }
        }

        // Output hasil
        if (berhasilSampaiPuncak) {
            System.out.println("Selamat Pendakian anda berhasil mencapai Puncak Mahameru, sisa tenaga anda " + energi);
        } else {
            System.out.println("Mohon maaf, istirahat hanya diperbolehkan di Pos - pos yang tersedia");
        }
    }

    static boolean cekLangkahValid(int x, int y, char langkah) {
        int newX = x;
        int newY = y;

        // Tentukan koordinat baru berdasarkan langkah
        switch (langkah) {
            case 'L':
                newY--;
                break;
            case 'R':
                newY++;
                break;
            case 'U':
                newX--;
                break;
            case 'D':
                newX++;
                break;
        }

        // Cek apakah koordinat baru valid dan di jalur hijau
        return (newX >= 0 && newX < 5 && newY >= 0 && newY < 5 && 
                petaPendakian[newX][newY] == 'H');
    }
}