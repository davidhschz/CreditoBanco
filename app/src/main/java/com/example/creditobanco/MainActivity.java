package com.example.creditobanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, fecha, valor;
    RadioButton vivienda, libreInversion, vehiculo, educacion, cuotas12, cuotas24, cuotas36;
    CheckBox cuotaManejo;
    TextView valorCuota, totalPrestamo;
    Button calcular, limpiar;
    RadioGroup rgintereses, rgcuotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.etnombre);
        fecha = findViewById(R.id.etfecha);
        valor = findViewById(R.id.etvalor);
        vivienda = findViewById(R.id.rbvivienda);
        libreInversion = findViewById(R.id.rblibre);
        vehiculo = findViewById(R.id.rbvehiculo);
        educacion = findViewById(R.id.rbeducacion);
        cuotas12 = findViewById(R.id.rbcuotas12);
        cuotas24 = findViewById(R.id.rbcuotas24);
        cuotas36 = findViewById(R.id.rbcuotas36);
        cuotaManejo = findViewById(R.id.cbcuotaManejo);
        valorCuota = findViewById(R.id.tvcuota);
        totalPrestamo = findViewById(R.id.tvtotalprestamo);
        calcular = findViewById(R.id.btncalcular);
        limpiar = findViewById(R.id.btnlimpiar);
        rgintereses = findViewById(R.id.rgintereses);
        rgcuotas = findViewById(R.id.rgcuotas);


        calcular.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        boolean checkRadioButton = true;


        if (rgintereses.getCheckedRadioButtonId() == -1 || rgcuotas.getCheckedRadioButtonId() == -1){
            checkRadioButton = false;
        }

        if (checkRadioButton && !nombre.getText().toString().isEmpty() && !fecha.getText().toString().isEmpty() && !valor.getText().toString().isEmpty()){

            double capital = Double.parseDouble(valor.getText().toString());
            double intereses = 0;
            double nCuotas = 0;
            double cManejo = 0;
            double valorTotalPrestamo = 0;
            double valorCuotaMensual;


            switch (rgintereses.getCheckedRadioButtonId()){
                case R.id.rbvivienda:
                    intereses = 0.01f;
                    break;
                case R.id.rblibre:
                    intereses = 0.02f;
                    break;
                case R.id.rbvehiculo:
                    intereses = 0.015f;
                    break;
                case R.id.rbeducacion:
                    intereses = 0.012f;
                    break;
            }
            // Cuotas

            switch (rgcuotas.getCheckedRadioButtonId()){
                case R.id.rbcuotas12:
                    nCuotas = 12;
                    break;
                case R.id.rbcuotas24:
                    nCuotas = 24;
                    break;
                case R.id.rbcuotas36:
                    nCuotas = 36;
                    break;
            }

            //Valor del prestamo
            if (cuotaManejo.isChecked()){
                cManejo = 10000 * nCuotas;
                valorTotalPrestamo = (capital * intereses) * nCuotas + capital;
                valorCuotaMensual = valorTotalPrestamo / nCuotas + 10000;
            }
            else{
                valorTotalPrestamo = (capital * intereses) * nCuotas + capital;
                valorCuotaMensual = valorTotalPrestamo / nCuotas ;
            }

            //Valor de las cuotas.
            DecimalFormat vcm = new DecimalFormat("###,###,###,###");


            totalPrestamo.setText(vcm.format(valorTotalPrestamo));
            valorCuota.setText(vcm.format(valorCuotaMensual));
        }
        else{
            Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                fecha.setText("");
                valor.setText("");
                rgcuotas.clearCheck();
                rgintereses.clearCheck();
                if (cuotaManejo.isChecked()){
                    cuotaManejo.toggle();
                }
                valorCuota.setText("");
                totalPrestamo.setText("");
            }
        });

    }

    /*vivienda.isChecked() || libreInversion.isChecked()|| vehiculo.isChecked() || educacion.isChecked() || cuotas12.isChecked() || cuotas24.isChecked() || cuotas36.isChecked() */
}