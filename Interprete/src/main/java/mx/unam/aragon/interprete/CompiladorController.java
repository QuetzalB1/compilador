package mx.unam.aragon.interprete;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CompiladorController {

    private GraphicsContext graficos;
    private Scene escena;
    private AnimationTimer tiempo;
    private ArrayList<String> comandos = new ArrayList<>();


    //banderas
    boolean fondoActivo = false;

    //variables especiales
    Color color = null;
    @FXML
    private Button bntEjecutar;

    @FXML
    private Button btnCompilar;

    @FXML
    private Canvas canvas;

    @FXML
    private TextArea txtCodigo;

    @FXML
    private TextArea txtMensajes;

    @FXML
    void actionCompilar(ActionEvent event) {

    }

    @FXML
    void actionEjecutar(ActionEvent event) {
        this.leerArchivo();
        this.lecturaComando();
        this.iniciar();
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public void iniciar() {
        componentesIniciar();
        //cerrarJuego();
        ciclo();

    }

    private void pintar() {
        if (fondoActivo) {
            graficos.fillRect(0, 0, 400, 400);
        }
        graficos.strokeRect(10, 10, 20, 20);
    }

    private void cerrarJuego() {
        Stage stage = (Stage) canvas.getScene().getWindow();
        stage.setOnCloseRequest((t) -> {
                    tiempo.stop();
                    stage.close();
                }
        );
    }

    private void componentesIniciar() {
        graficos = canvas.getGraphicsContext2D();
    }

    private void ciclo() {
        long tiempoInicio = System.nanoTime();
        tiempo = new AnimationTimer() {
            @Override
            public void handle(long tiempoActual) {
                double t = (tiempoActual - tiempoInicio) / 1000000000.0;
                System.out.println((int)t%5);
                if((int)t%5==3){
                    lecturaComando();
                    pintar();
                }

            }

        };
        tiempo.start();
    }

    private void leerArchivo() {
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");
        comandos.add("f,red");
        comandos.add("lpr");

    }

    private void lecturaComando() {
            String[] comando = comandos.remove(0).split(",");
            //comando de color de fondo
            if (comando[0].equals("f")) {
                fondoActivo = true;
                if (comando[1].equals("red")) {
                    this.color = Color.RED;
                }
            }
            //comando limpiar
            if (comando[0].equals("lpr")) {
                fondoActivo = false;
            }
            if(comandos.isEmpty()){
                System.out.println("Mato");
                tiempo.stop();
            }
        }

}
