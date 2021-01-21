function validateName() {
    return validateEmpty("name");
}
function validatePrice() {
    return validateRegex("price",/^\d+(\.\d{1,2})?$/);
}
function validateAmount() {
    return validateRegex("amount",/^\d+$/);
}
function validateInsert() {
    return validateName()&&validatePrice()&&validateAmount();
}