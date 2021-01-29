function calculate(mass, height, age, sex) {
    mass = document.getElementById(mass).value;
    console.log(mass);
    height = document.getElementById(height).value;
    console.log(height);
    age = document.getElementById(age).value;
    console.log(age);
    sex = document.getElementById(sex).value;
    console.log(sex);
    let result = 9.99 * mass + 6.25 * height - 4.92 * age;
    if(sex !== "male"){
        result -= 161;
    } else{
        result += 5;
    }
    document.getElementById("result").innerHTML = "Zapotrzebowanie kaloryczne wynosi: " + result + " kalorii";
}