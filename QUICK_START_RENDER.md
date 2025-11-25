# 🚀 Quick Start: Deploy to Render

## What We Just Set Up

✅ **GitHub Actions Workflow** — Auto-builds and deploys on every push  
✅ **Docker Hub Integration** — Stores your Docker images  
✅ **Render Deployment** — Hosts your app on Render's free tier  

---

## Quick Setup (5 minutes)

### 1️⃣ Create Docker Hub Account
- Visit [hub.docker.com](https://hub.docker.com) → Sign up (free)
- Create Personal Access Token (PAT) in Account Settings

### 2️⃣ Create Render Account
- Visit [render.com](https://render.com) → Sign up (free)
- Create Web Service with Docker image URL

### 3️⃣ Add GitHub Secrets
In your GitHub repo:
- **Settings** → **Secrets and variables** → **Actions**
- Add 4 secrets:
  - `DOCKER_HUB_USERNAME`
  - `DOCKER_HUB_TOKEN`
  - `RENDER_SERVICE_ID`
  - `RENDER_API_KEY`

### 4️⃣ Push to GitHub
```bash
git remote add origin https://github.com/YOUR_USERNAME/employee-management.git
git push -u origin master
```

### 5️⃣ Watch GitHub Actions
- Go to **Actions** tab
- Watch workflow run automatically
- Check deployment on Render dashboard

---

## Deployment Flow

```
Your Code Change
    ↓
Git Push to GitHub
    ↓
GitHub Actions Triggered
    ↓
Build with Maven ✅
    ↓
Build Docker Image ✅
    ↓
Push to Docker Hub ✅
    ↓
Deploy to Render ✅
    ↓
Live at https://your-app.onrender.com 🎉
```

---

## Important Files

| File | Purpose |
|------|---------|
| `.github/workflows/deploy-to-render.yml` | GitHub Actions CI/CD pipeline |
| `.gitignore` | Excludes build artifacts from git |
| `RENDER_DEPLOYMENT.md` | Detailed setup guide |
| `Dockerfile` | Containerizes the Spring Boot app |
| `pom.xml` | Maven dependencies |

---

## Test Your Deployment

Once live on Render:

```bash
# Get all employees
curl https://your-app.onrender.com/api/employees

# Create employee
curl -X POST https://your-app.onrender.com/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Bob","lastName":"Smith","email":"bob@example.com","department":"IT","salary":60000}'
```

---

## Free Tier Limits (Render)

- **Compute**: 0.5 CPU, 512 MB RAM (free)
- **Storage**: 1 GB (free)
- **Bandwidth**: Limited but sufficient for testing
- **Sleep**: App spins down after 15 mins of inactivity (free tier)

---

## Costs

| Service | Free Tier | Cost |
|---------|-----------|------|
| GitHub Actions | 2000 min/month | Free |
| Docker Hub | Unlimited images | Free |
| Render | Yes (limited) | $0 (upgrades available) |
| **Total** | **Free** | **$0** |

---

## Next Steps

1. Follow the detailed guide in `RENDER_DEPLOYMENT.md`
2. Set up secrets in GitHub
3. Push to GitHub
4. Watch automatic deployment
5. Test on Render's public URL

---

## Questions?

Check these files:
- `RENDER_DEPLOYMENT.md` — Full step-by-step guide
- `.github/workflows/deploy-to-render.yml` — Workflow configuration
- `README.md` — Application documentation

Happy deploying! 🚀
