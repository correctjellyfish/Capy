{
  pkgs,
  lib,
  config,
  inputs,
  ...
}: {
  packages = [pkgs.git];
  # Enable java
  languages.java = {
    enable = true;
    jdk.package = pkgs.jdk;
    # Enable Maven
    maven = {
      enable = true;
    };
  };
}
