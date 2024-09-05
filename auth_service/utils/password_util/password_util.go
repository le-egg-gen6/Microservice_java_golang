package password_util

import "golang.org/x/crypto/bcrypt"

func CompareHashedPassword(password string, storedPassword string) bool {
	return bcrypt.CompareHashAndPassword([]byte(storedPassword), []byte(password)) == nil
}

func GenerateEncryptedPassword(password string) (string, error) {
	encryptedPassword, err := bcrypt.GenerateFromPassword([]byte(password), bcrypt.DefaultCost)
	if err != nil {
		return "", err
	}
	return string(encryptedPassword), nil
}