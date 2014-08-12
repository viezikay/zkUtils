//SpriteBatch will use texture unit 0
uniform sampler2D u_sampler2D;

//"in" varyings from our vertex shader
varying vec4 vColor;
varying vec2 vTexCoord;

void main() {
    //sample the texture
    vec4 texColor = texture2D(u_sampler2D, vTexCoord);

    //invert the red, green and blue channels
    texColor.rgb = 1.0 - texColor.rgb;

    //final color
    gl_FragColor = vColor * texColor;
}