package model

import (
	"context"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

const (
	CollectionUser = "c_user"
)

type User struct {
	ID       primitive.ObjectID `bson:"id"`
	Name     string             `bson:"name"`
	Email    string             `bson:"email"`
	Username string             `bson:"username"`
	Password string             `bson:"password"`
}

type UserRepository interface {
	Create(ctx context.Context, user *User) error
	GetByUsername(ctx context.Context, username string) (User, error)
	GetByEmail(ctx context.Context, email string) (User, error)
	GetByID(ctx context.Context, id string) (User, error)
}
