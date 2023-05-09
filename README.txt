Hemos quitado de la interficie el método all() ya que nosotros empezamos directamente importando cada tabla, en un HashMap.
Y eso nos permite leer más rápidamente las tablas y poder mostrarlas más rapido porque no tenemos que ir consultando a la base de datos.

Hemos quitado el count() de la interficie, al no tener variables diferentes segun la clase en la que se crea pensamos que con poner solo una en el que te pida el código de la tabla ya es suficiente.