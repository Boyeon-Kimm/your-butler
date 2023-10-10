# Use an official Python runtime as the base image
FROM python:3.11.5-slim

# Set the working directory in the container
WORKDIR /flask_server

# Install pipenv
RUN pip install pipenv

# Copy Pipfile and Pipfile.lock to ensure both are copied
COPY Pipfile Pipfile.lock /flask_server/

# Install dependencies using pipenv
RUN pipenv install --deploy --ignore-pipfile

# Copy the current directory contents into the container
COPY . /flask_server/

# Make port 5000 available to the outside world
EXPOSE 8000

ENV PYTHONPATH "${PYTHONPATH}:/flask_server"

# Define the command to run the app using pipenv
CMD ["pipenv", "run", "flask", "run", "--host=0.0.0.0", "--port=8000"]
