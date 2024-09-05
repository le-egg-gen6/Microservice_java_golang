package repository

import (
	"auth_service/model"
	"auth_service/mongo"
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type UserRepositoryImpl struct {
	database   mongo.Database
	collection string
}

func NewUserRepository(database mongo.Database, collection string) model.UserRepository {
	return &UserRepositoryImpl{
		database:   database,
		collection: collection,
	}
}

func (ur *UserRepositoryImpl) Create(ctx context.Context, user *model.User) error {
	collection := ur.database.Collection(ur.collection)

	_, err := collection.InsertOne(ctx, user)

	return err
}

func (ur *UserRepositoryImpl) GetByUsername(ctx context.Context, username string) (model.User, error) {
	collection := ur.database.Collection(ur.collection)

	var user model.User

	err := collection.FindOne(ctx, bson.M{"username": username}).Decode(&user)

	return user, err
}

func (ur *UserRepositoryImpl) GetByEmail(ctx context.Context, email string) (model.User, error) {
	collection := ur.database.Collection(ur.collection)

	var user model.User

	err := collection.FindOne(ctx, bson.M{"email": email}).Decode(&user)

	return user, err

}

func (ur *UserRepositoryImpl) GetByID(ctx context.Context, id string) (model.User, error) {
	collection := ur.database.Collection(ur.collection)

	var user model.User

	idHex, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		return user, err
	}

	err = collection.FindOne(ctx, bson.M{"_id": idHex}).Decode(&user)
	return user, err
}
