package middleware

import "github.com/gin-gonic/gin"

func InternalApiMiddlewareFilter(secret string) gin.HandlerFunc {
	return func(c *gin.Context) {
		internalHeader := c.Request.Header.Get("Internal")
		if internalHeader == "" {
			//
			c.Abort()
			return
		}
		isInternal := internalHeader == secret
		if isInternal {
			c.Next()
			return
		}
		//
		c.Abort()
	}
}
