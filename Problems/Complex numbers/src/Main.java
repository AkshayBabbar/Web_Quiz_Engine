class Complex {

    double real;
    double image;
    void add(Complex num){
        
        image = this.image + num.image;
        real = this.real + num.real;
    }
    void subtract(Complex num){
        
        image = this.image - num.image;
        real = this.real - num.real;
    }

    // write methods here
}
