//CHANGELOD - Calculadora cientifica en Java NeatBeans Swing 

Sprint 1 (Funcionalidades basicas, números, parentesis y de limpiexa) (19/11/2025-21/11/2025)
//Corregido 
-Correción de resultado de parentesis al estar solo limitado a operacion simples de separación de espacios.

//Cambiado
-Los parentesis se modificaron para poder realizar las operaciones noormalizando los operadores:
× → *
÷ → /
^ → Math.pow()

Aunado al uso de evaluador de ScriptEngine.

Sprint 2 ( Operaciones avanzadas, funcionalidades algoritmicas, exponenciales y constantes) (22/11/2025-25/11/2025)

//Corregido 
-Corrección de expresiones avanzadas que fallaban por el mismo límite inicial de separación de 
espacios y por símbolos Unicode no interpretables por el evaluador.

//Cambiado
-LNormalización de operadores para compatibilidad con el motor evaluador:

× → *
÷ → /
^ → Math.pow() / **

Aunado al uso de ScriptEngine. 

Sprint 3 (Funcionalidaes trigronometricas, de memoria e inversas) (26/11/2025-29/11/2025)

//Corregido

Corrección del resultado de funciones trigonométricas (sin, cos, tan) debido a que inicialmente
usaban valores en grados sin conversión o tomaban números incorrectos desde el display, produciendo 
resultados inconsistentes.

//Cambiado

Ajuste en la lógica trigonométrica para operar correctamente sobre el último número ingresado.
Estandarización de salida numérica para evitar errores por expresiones parciales.





