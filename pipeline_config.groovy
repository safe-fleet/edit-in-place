libraries {
  core {
    slack_channel = 'vmax-alerts'
    node_label = 'docker-builds-slave'
    code_style = false
    github_packages = true

    semantic_release {
      enabled = true
      config_type = 'global'
      name = 'edit-in-place'
      repository = 'safe-fleet/edit-in-place'
      artifacts = 'docker'
      issues = 'jira'
    }
  }

  nodejs {
    build_image = 'node:20.10.0-alpine3.18'

    build {
      commands = 'npm ci,npm run build:lib'
      pr_commands = 'npm ci'
      npmrc_file = 'npmrc'
    }

    sonarqube_analysis {
      project_key = 'edit-in-place'
      project_name = 'edit-in-place'
      sources = 'projects/ngneat/edit-in-place/src'
      exclusions = "**/*"
      coverage_exclusions = "**/*"
    }

    publish {
      project_folder = 'dist/edit-in-place'
    }
  }
}
