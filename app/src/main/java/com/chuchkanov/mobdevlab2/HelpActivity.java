package com.chuchkanov.mobdevlab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    ArrayList<Helps> helps = new ArrayList<Helps>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list_help);
        PhotoAdapter adapter = new PhotoAdapter(this, helps);
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){

        helps.add(new Helps("Головний екран - можна обрати одну з 4-х опцій, щоб підтвердити вибір треба натиснути кнопку Обрати",R.drawable.help1));
        helps.add(new Helps("Пункт База даних: Додати область - додає область, якщо дані будуть введені коректно." +
                " Інакше виводить повідомлення про помилку. Вибірка - виводить усі області, населення яких не перемищує введене значення." +
                " При натисненні на облась вона відкриється на карті",R.drawable.help2));
        helps.add(new Helps("Результат натиснення на область",R.drawable.help9));
        helps.add(new Helps("Пункт Контакти: виводить всі контакти, що починаються на +38097",R.drawable.help4));
        helps.add(new Helps("Пункт Афінний шифр: потрібно ввести ключ у форматі \"a,b\", де а взаємно просте з числом 26. Текст - a-zA-Z. При неправильному форматі ключа або тексту буде виведено повідомлення з підказкою." +
                "Зберегти дані - зберігає вхідні та вихідні дані. Завантажити їх можна окремо. Копіювати - копіює результуючий текст у вхідний текст. Графік - будує гістограму з 5-ма найчастішими літерами у вихідному тексті.",R.drawable.help5));
        helps.add(new Helps("\"Гістограма для тексту \"test\"\"",R.drawable.help7));
        helps.add(new Helps("Пункт Шифр Цезаря - аналогічно до афінного шифру, крім ключа - ключ має бути числом.",R.drawable.help8));

    }
}