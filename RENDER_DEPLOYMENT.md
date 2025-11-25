# Deploy to Render using GitHub Actions

This guide explains how to deploy the Spring Boot Employee Management application to Render using GitHub Actions.

## Prerequisites

1. **GitHub Account** — with this repository
2. **Docker Hub Account** — to store Docker images (free tier available)
3. **Render Account** — to host the application (free tier available)

## Step-by-Step Setup

### 1. Create Docker Hub Account & Get Credentials

1. Go to [Docker Hub](https://hub.docker.com/) and sign up (free)
2. Click your profile icon → **Account Settings** → **Security**
3. Create a new **Personal Access Token** (PAT):
   - Click **"New Access Token"**
   - Name: `github-actions`
   - Permissions: Read, Write, Delete
   - Copy the token (you won't see it again)

### 2. Create Render Account & Web Service

1. Go to [Render](https://render.com/) and sign up (free)
2. Create a new **Web Service**:
   - Name: `employee-management-app`
   - Runtime: **Docker**
   - Docker image URL: `<your-docker-hub-username>/employee-management:latest`
   - Port: `8080`
   - Plan: **Free** (optional, but recommended to start)
3. After creation, get your **Service ID**:
   - Go to service settings
   - Copy the service ID from the URL or settings page
4. Create an **API Key**:
   - Go to Render Dashboard → Settings → API Keys
   - Create new key, copy it

### 3. Add GitHub Secrets

1. Go to your GitHub repository
2. Click **Settings** → **Secrets and variables** → **Actions**
3. Click **"New repository secret"** and add these:

| Secret Name | Value |
|-------------|-------|
| `DOCKER_HUB_USERNAME` | Your Docker Hub username |
| `DOCKER_HUB_TOKEN` | Your Docker Hub Personal Access Token |
| `RENDER_SERVICE_ID` | Your Render Web Service ID |
| `RENDER_API_KEY` | Your Render API Key |

**Example:**
```
DOCKER_HUB_USERNAME = myusername
DOCKER_HUB_TOKEN = dckr_pat_abc123xyz...
RENDER_SERVICE_ID = srv_abc123xyz
RENDER_API_KEY = rnd_abc123xyz...
```

### 4. Push to GitHub

1. Add the .gitignore file:
```bash
git add .gitignore
git commit -m "Add .gitignore for Maven and IDE files"
git push origin master
```

2. Create GitHub remote (first time only):
```bash
# If you created a new repo on GitHub, add it as remote
git remote add origin https://github.com/YOUR_USERNAME/employee-management.git
git branch -M master
git push -u origin master
```

### 5. Verify GitHub Actions Workflow

1. Go to your GitHub repo → **Actions** tab
2. You should see "Build and Deploy to Render" workflow running
3. Watch the steps:
   - ✅ Checkout code
   - ✅ Build with Maven
   - ✅ Build Docker image
   - ✅ Push to Docker Hub
   - ✅ Deploy to Render

### 6. Verify Deployment on Render

1. Go to your Render dashboard
2. Click on your service (`employee-management-app`)
3. Under **Logs**, you should see:
   - `Container started successfully`
   - Port `8080` is listening
4. Get your public URL from the dashboard (e.g., `https://employee-management-app.onrender.com`)

## Testing the Deployed App

Once deployed, test the API:

```bash
# Get all employees
curl https://your-render-app-url.onrender.com/api/employees

# Create an employee
curl -X POST https://your-render-app-url.onrender.com/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "department": "IT",
    "salary": 50000
  }'
```

## Continuous Deployment Flow

After setup, every time you push to `master` or `main` branch:

1. **GitHub Actions** automatically triggers
2. **Maven** builds the JAR
3. **Docker** builds the image
4. **Docker Hub** stores the image
5. **Render** pulls the new image and redeploys
6. **Application** is live at your Render URL

## Troubleshooting

### Build fails in GitHub Actions
- Check **Actions** tab → workflow logs for errors
- Ensure `pom.xml` and `Dockerfile` are correct
- Verify Java 17 is available

### Docker Hub push fails
- Verify `DOCKER_HUB_USERNAME` and `DOCKER_HUB_TOKEN` secrets are correct
- Make sure Docker Hub account credentials are valid

### Render deployment doesn't update
- Check Render dashboard → Logs
- Verify `RENDER_SERVICE_ID` and `RENDER_API_KEY` are correct
- Manually trigger deployment from Render dashboard if needed

### App crashes on Render
- Check Render logs for errors
- Verify environment variables (if needed)
- Ensure database configuration is correct (H2 in-memory is pre-configured)

## Manual Commands (for reference)

```bash
# Build locally
mvn clean package

# Build Docker image
docker build -t myusername/employee-management:latest .

# Push to Docker Hub
docker push myusername/employee-management:latest

# Run locally
docker run -p 8080:8080 myusername/employee-management:latest
```

## Costs

- **GitHub Actions**: Free (2000 minutes/month for private repos)
- **Docker Hub**: Free (unlimited public images)
- **Render**: Free tier available (limited resources, but sufficient for testing)

## Next Steps

1. Add more sophisticated CI/CD (tests, code analysis, security scanning)
2. Set up staging environment for testing before production
3. Add automatic scaling on Render
4. Set up monitoring and alerting
5. Add database (PostgreSQL) instead of H2

## Support

For issues:
- [Render Docs](https://render.com/docs)
- [GitHub Actions Docs](https://docs.github.com/en/actions)
- [Docker Hub Docs](https://docs.docker.com/docker-hub/)
