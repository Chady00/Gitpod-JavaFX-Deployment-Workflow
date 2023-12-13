# Use the gitpod/workspace-full-vnc image as a base
FROM gitpod/workspace-full-vnc

# Switch to root user temporarily to run apt-get commands
USER root

# Install additional packages for GUI development
RUN apt-get update \
    && apt-get install -y openjfx libopenjfx-java matchbox \
    && apt-get clean \
    && rm -rf /var/cache/apt/* /var/lib/apt/lists/* /tmp/*

# Switch back to the non-root user
USER gitpod
