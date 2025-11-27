@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String apellido = "";

    @NotBlank
    @Email
    @Size(max = 150)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password_hash")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol = Rol.USUARIO;

    @Size(max = 30)
    private String telefono;

    @Size(max = 255)
    private String direccion;

    @Column(name = "foto_url")
    private String fotoUrl;

    // ❌ QUITA el columnDefinition TINYINT(1)
    @Column
    private Boolean activo = Boolean.TRUE;

    @Column
    private Boolean verificado = Boolean.FALSE;

    @Column(name = "fecha_creacion", updatable = false, insertable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", insertable = false, updatable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "recordatorios_activos")
    private Boolean recordatoriosActivos = Boolean.FALSE;

    @Column(name = "qr_token", length = 64, unique = true)
    private String qrToken;

    @Column(name="email_verificado")
    private boolean emailVerificado = false;

    // getters y setters igual que los que ya tienes...
}
