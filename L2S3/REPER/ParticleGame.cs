using System.Collections;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using REPER.Core;

namespace REPER;

public class ParticleGame: Game{

    private GraphicsDeviceManager _graphics;
    private SpriteBatch _spriteBatch;

	private Camera _camera;
	private World _world;
	private ArrayList _objects = [];

    public ParticleGame(){
        _graphics = new GraphicsDeviceManager(this);

        Content.RootDirectory = "Content";
        IsMouseVisible = true;

		_world = new World(this);
		var player = new Particle(this);
		_camera = new Camera(this, player);

		_objects.Add(_world);
		_objects.Add(_camera);
		_objects.Add(player);
    }

	// GETTERS
	private DisplayMode GetDisplayMode(){
		return _graphics.GraphicsDevice.DisplayMode;
	}

	public Viewport GetViewport(){
		return _graphics.GraphicsDevice.Viewport;
	}

	// FUNCTIONS
    protected override void Initialize(){
		_graphics.PreferredBackBufferWidth = GetDisplayMode().Width/2;
		_graphics.PreferredBackBufferHeight = GetDisplayMode().Height/2;
		_graphics.ApplyChanges();

		Window.AllowUserResizing = true;

		foreach(GameObject go in _objects) go.Initialize();

		base.Initialize();
    }
    protected override void LoadContent() {
        _spriteBatch = new SpriteBatch(GraphicsDevice);

		foreach(GameObject go in _objects) go.LoadContent(Content);

		_camera.SetSpaceBounds(_world.GetBounds());
    }

    protected override void Update(GameTime gameTime){
        if(GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed
			|| Keyboard.GetState().IsKeyDown(Keys.Escape)
		)
            Exit();

		foreach(GameObject go in _objects) go.Update(gameTime);
		foreach(GameObject go in _objects) go.Move(gameTime);
		foreach(GameObject go in _objects)
			if(go.HasPhysics())
				foreach(GameObject othergo in _objects)
					go.Collide(othergo);

        base.Update(gameTime);
    }
    protected override void Draw(GameTime gameTime){
        GraphicsDevice.Clear(Color.CornflowerBlue);

		_spriteBatch.Begin();
		foreach(GameObject go in _objects)
			if(go is not Camera)
				go.Draw(_spriteBatch, _camera.GetPosition());
		_spriteBatch.End();

        base.Draw(gameTime);
    }
}
