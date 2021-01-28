function calculate(mass, height, age, sex) {
    var mass = document.getElementById(mass).value;
    console.log(mass);
    var height = document.getElementById(height).value;
    console.log(height);
    var age = document.getElementById(age).value;
    console.log(age);
    var sex = document.getElementById(sex).value;
    console.log(sex);
    var result = 9.99*mass + 6.25*height - 4.92*age;
    //(9,99 x masa ciała) + (6,25 x wzrost) – (4,92 x wiek) - 161/+5
    if(sex != "male"){
        result -= 161;
    } else{
        result += 5;
    }
    document.getElementById("result").innerHTML = "Zapotrzebowanie kaloryczne wynosi: " + parseInt(result) + "kalorii";
}