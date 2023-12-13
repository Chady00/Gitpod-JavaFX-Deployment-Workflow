# Use the gitpod/workspace-full-vnc image as a base
FROM gitpod/workspace-full-vnc

# Install additional packages using gp command
USER gitpod
RUN gp install openjfx libopenjfx-java matchbox
