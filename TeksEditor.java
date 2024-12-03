import java.util.Stack;

// Kelas TextEditor untuk simulasi editor teks
class TextEditor {
    private String currentText;      // Teks saat ini yang ada di editor
    private Stack<String> undoStack; // Stack untuk menyimpan riwayat undo
    private Stack<String> redoStack; // Stack untuk menyimpan riwayat redo

    // Konstruktor untuk menginisialisasi TextEditor
    public TextEditor() {
        currentText = "";             // Teks awal kosong
        undoStack = new Stack<>();    // Stack untuk undo
        redoStack = new Stack<>();    // Stack untuk redo
    }

    // Fungsi untuk menampilkan teks saat ini
    public void show() {
        System.out.println("Current Text: " + currentText);
    }

    // Fungsi untuk menulis teks baru
    public void write(String text) {
        // Simpan teks saat ini ke dalam undo stack sebelum menulis teks baru
        undoStack.push(currentText);
        currentText += text;
        // Bersihkan redo stack setelah menulis baru, karena redo tidak valid lagi
        redoStack.clear();
    }

    // Fungsi untuk melakukan undo
    public void undo() {
        if (!undoStack.isEmpty()) {
            // Pindahkan teks saat ini ke redo stack
            redoStack.push(currentText);
            // Kembalikan teks ke keadaan sebelumnya
            currentText = undoStack.pop();
            System.out.println("Undo performed.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Fungsi untuk melakukan redo
    public void redo() {
        if (!redoStack.isEmpty()) {
            // Pindahkan teks saat ini ke undo stack
            undoStack.push(currentText);
            // Kembalikan teks ke keadaan yang lebih baru
            currentText = redoStack.pop();
            System.out.println("Redo performed.");
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        // Menulis teks pertama
        editor.write("Hello, ");
        editor.show();  // Output: "Hello, "

        // Menulis teks kedua
        editor.write("world!");
        editor.show();  // Output: "Hello, world!"

        // Melakukan undo
        editor.undo();
        editor.show();  // Output: "Hello, "

        // Melakukan redo
        editor.redo();
        editor.show();  // Output: "Hello, world!"

        // Menulis teks ketiga
        editor.write(" How are you?");
        editor.show();  // Output: "Hello, world! How are you?"

        // Melakukan undo
        editor.undo();
        editor.show();  // Output: "Hello, world!"

        // Melakukan redo
        editor.redo();
        editor.show();  // Output: "Hello, world! How are you?"

        // Melakukan undo beberapa kali
        editor.undo();
        editor.show();  // Output: "Hello, world!"
        editor.undo();
        editor.show();  // Output: "Hello, "

        // Melakukan redo
        editor.redo();
        editor.show();  // Output: "Hello, world!"
    }
}