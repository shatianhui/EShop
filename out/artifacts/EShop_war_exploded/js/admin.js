function validateAid() {
    return validateEmpty("aid");
}
function validatePas() {
    return validateEmpty("password");
}
function validateCode() {
    return validateEmpty("code");
}
function validateLogin() {
    return validateAid()&&validatePas()&&validateCode();
}