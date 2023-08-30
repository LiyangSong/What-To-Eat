# Use Node runtime as base image
FROM node:18-slim AS build

# Set working directory
WORKDIR /usr/src/app

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy the project files and build the project.
COPY . .
RUN npm run build

# Use nginx to run the compiled app
FROM nginx:alpine

# Copy the build folder from build image to nginx
COPY --from=0 /app/build /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
