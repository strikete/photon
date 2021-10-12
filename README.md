# photon
ETC EOS Osc &amp; Csv Data Modeling for Java.

Welcome to photon!
I'm still developing a working version at this time (October 2021), but here's the vague idea of this project:
  1. Photon polls ETC EOS consoles via OSC (Open Sound Control) for data. It then creates extremely basic POJOs (Plain Ol' Java Objects) that represent the data types. (Groups, Channels, Palettes, you get the idea)
  2. Photon can also process CSV files from ETC EOS consoles and create a more complete data model with levels and parameter data.
  3. Photon serves as an API to easily complete this process for other programs to build off of. Below are two hypothetical uses.
 
Hypothetical use #1:
    A find and replace feature. i.e. Change all times Channel 8 is at Full to 80%

Hypothetical use #2:
    Usage report generator. Photon can provide you the raw data about how many times a Color Palette or other object is used, and on what fixtures it's used most.
    Could be useful in finding out what colors are frequented by designers, or even the carbon footprint of a show.
    
There are more uses I can think of, but these are just a few examples.
