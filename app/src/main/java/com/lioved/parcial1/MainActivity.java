package com.lioved.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Nombre;
    EditText Salario;
    EditText Mes;
    Button btnCalcular;
    EditText Prima;
    EditText Cesantias;
    EditText Vacaciones;
    EditText Salud;
    EditText Pension;
    EditText CajaCompensacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        Nombre = findViewById(R.id.editTextNombre);
        Salario = findViewById(R.id.editTextSalario);
        Mes = findViewById(R.id.editTextTextMes);
        Prima = findViewById(R.id.txtPrima);
        Cesantias = findViewById(R.id.txtCesantias);
        Vacaciones = findViewById(R.id.txtVacaciones);
        Salud = findViewById(R.id.txtSalud);
        Pension = findViewById(R.id.txtPension);
        CajaCompensacion  =findViewById(R.id.txtCajaCompensacion);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);
    }


    public int CalcularPrimaCesantias(int salario, String mes){
        int prima = 0;
        if((mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre"))){

            if (salario<1817052){
                int auxilioTransporte = 106454;
                prima = ((salario+auxilioTransporte)*(31))/360;
            }else {
                prima = ((salario)*(31))/360;
            }
        }

        if (mes.equals("Febrero")){
            if (salario<1817052){
                int auxilioTransporte = 106454;
                prima = ((salario+auxilioTransporte)*(28))/360;
            }else {
                prima = ((salario)*(28))/360;
            }
        }

        if (mes.equals("Abril")||mes.equals("Mayo")||mes.equals("Junio")||mes.equals("Septiembre")||mes.equals("Noviembre")){
            if (salario<1817052){
                int auxilioTransporte = 106454;
                prima = ((salario+auxilioTransporte)*(30))/360;
            }else {
                prima = ((salario)*(30))/360;
            }
        }
        return prima;
    }

    public int CalcularVacaciones(int salario, String mes) {
        int vacaciones = 0;
        if ((mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre"))) {
            vacaciones = ((salario) * (31)) / 720;
        }

        if (mes.equals("Febrero")){
            vacaciones = ((salario)*(28))/720;
        }

        if (mes.equals("Abril")||mes.equals("Mayo")||mes.equals("Junio")||mes.equals("Septiembre")||mes.equals("Noviembre")){
            vacaciones = ((salario)*(30))/720;
        }

        return vacaciones;
    }

    public int Calcular_Salud_Pension_Caja(int salario){
        int aportes= (salario*4)/(100);

        return  aportes;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCalcular:{
                int salario = Integer.parseInt(Salario.getText().toString());
                String mes = Mes.getText().toString();
                int prima = CalcularPrimaCesantias(salario, mes);
                int cesantias = prima;
                int vacaciones = CalcularVacaciones(salario, mes);
                int aportes = Calcular_Salud_Pension_Caja(salario);
                Toast.makeText(this, "El saldo de la prima es: "+ prima, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "El saldo de las censatias es : "+ cesantias, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "El saldo de vacaiones es: "+ vacaciones, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "El aporte a salud es: "+ aportes, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "EL aporte a pension es: "+ prima, Toast.LENGTH_LONG).show();
                Toast.makeText(this, "EL aporte a caja de compensacion: "+ prima, Toast.LENGTH_LONG).show();
                Prima.setText(""+prima);
                Cesantias.setText(""+cesantias);
                Vacaciones.setText(""+vacaciones);
                Salud.setText(""+aportes);
                Pension.setText(""+aportes);
                CajaCompensacion.setText(""+aportes);
            }
        }
    }
}